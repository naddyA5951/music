package com.example.audio

import android.content.ContentUris
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import android.media.audiofx.Virtualizer
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.data.MusicCatalog
import com.example.data.local.DownloadedTrackEntity
import com.example.data.local.MusicDatabase
import com.example.model.EqualizerBand
import com.example.model.EqualizerState
import com.example.model.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.File

enum class RepeatMode {
    OFF, ALL, ONE
}

class MusicPlayerManager(private val context: Context) {

    private val tag = "MusicPlayerManager"
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val db = MusicDatabase.getDatabase(context)

    private var mediaPlayer: MediaPlayer? = null
    private var progressJob: Job? = null

    // Audio effects
    private var equalizerFx: Equalizer? = null
    private var bassBoostFx: BassBoost? = null
    private var virtualizerFx: Virtualizer? = null

    // State
    private val _currentTrack = MutableStateFlow<Track?>(null)
    val currentTrack: StateFlow<Track?> = _currentTrack.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPositionMs = MutableStateFlow(0L)
    val currentPositionMs: StateFlow<Long> = _currentPositionMs.asStateFlow()

    private val _durationMs = MutableStateFlow(0L)
    val durationMs: StateFlow<Long> = _durationMs.asStateFlow()

    private val _isBuffering = MutableStateFlow(false)
    val isBuffering: StateFlow<Boolean> = _isBuffering.asStateFlow()

    private val _isShuffleEnabled = MutableStateFlow(false)
    val isShuffleEnabled: StateFlow<Boolean> = _isShuffleEnabled.asStateFlow()

    private val _repeatMode = MutableStateFlow(RepeatMode.ALL)
    val repeatMode: StateFlow<RepeatMode> = _repeatMode.asStateFlow()

    private val _equalizerState = MutableStateFlow(EqualizerState())
    val equalizerState: StateFlow<EqualizerState> = _equalizerState.asStateFlow()

    private val _downloadProgress = MutableStateFlow<Map<String, Float>>(emptyMap())
    val downloadProgress: StateFlow<Map<String, Float>> = _downloadProgress.asStateFlow()

    // Playlist Queue
    private val queue = mutableListOf<Track>()
    private var queueIndex = 0

    init {
        // Pre-fill queue with initial catalog
        setQueue(MusicCatalog.sampleTracks, 0, autoPlay = false)
    }

    fun setQueue(tracks: List<Track>, startIndex: Int = 0, autoPlay: Boolean = true) {
        if (tracks.isEmpty()) return
        queue.clear()
        queue.addAll(tracks)
        queueIndex = startIndex.coerceIn(0, queue.size - 1)
        val selected = queue[queueIndex]
        _currentTrack.value = selected
        _durationMs.value = selected.durationMs
        _currentPositionMs.value = 0L

        if (autoPlay) {
            playTrack(selected)
        }
    }

    fun playTrack(track: Track) {
        _currentTrack.value = track
        _durationMs.value = track.durationMs
        _isBuffering.value = true

        scope.launch(Dispatchers.IO) {
            try {
                // Determine audio source:
                // 1. Local device file / content URI if device track
                // 2. Downloaded file in audio_downloads
                // 3. Cached synthetic audio file
                val audioUri: Uri? = when {
                    track.localFilePath?.startsWith("content://") == true -> {
                        Uri.parse(track.localFilePath)
                    }
                    else -> null
                }

                val audioFile: File? = if (audioUri == null) {
                    when {
                        track.localFilePath != null && File(track.localFilePath).exists() -> {
                            File(track.localFilePath)
                        }
                        else -> {
                            // Check if downloaded
                            val downloadRecord = db.musicDao().getDownload(track.id)
                            if (downloadRecord != null && File(downloadRecord.localFilePath).exists()) {
                                File(downloadRecord.localFilePath)
                            } else {
                                // Synthesize or get from cache
                                AudioSynthesizer.getOrCreateAudioFile(
                                    context = context,
                                    trackId = track.id,
                                    genre = track.genre,
                                    durationSeconds = (track.durationMs / 1000).toInt().coerceAtLeast(60)
                                )
                            }
                        }
                    }
                } else null

                launch(Dispatchers.Main) {
                    startMediaPlayer(audioFile, audioUri, track)
                }
            } catch (e: Exception) {
                Log.e(tag, "Failed to prepare audio track", e)
                _isBuffering.value = false
            }
        }
    }

    private fun startMediaPlayer(file: File?, uri: Uri?, track: Track) {
        try {
            mediaPlayer?.release()
            releaseAudioEffects()

            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                if (uri != null) {
                    setDataSource(context, uri)
                } else if (file != null) {
                    setDataSource(file.absolutePath)
                } else {
                    throw IllegalStateException("Neither file nor Uri provided for playback")
                }
                setOnPreparedListener { mp ->
                    _isBuffering.value = false
                    _durationMs.value = mp.duration.toLong()
                    mp.start()
                    _isPlaying.value = true
                    startProgressTracker()
                    attachAudioEffects(mp.audioSessionId)
                }
                setOnCompletionListener {
                    handleTrackCompletion()
                }
                setOnErrorListener { _, what, extra ->
                    Log.e(tag, "MediaPlayer error: what=$what, extra=$extra")
                    _isBuffering.value = false
                    _isPlaying.value = false
                    true
                }
                prepareAsync()
            }
        } catch (e: Exception) {
            Log.e(tag, "Failed to start MediaPlayer", e)
            _isBuffering.value = false
        }
    }

    fun togglePlayPause() {
        val mp = mediaPlayer
        if (mp != null) {
            if (mp.isPlaying) {
                mp.pause()
                _isPlaying.value = false
                stopProgressTracker()
            } else {
                mp.start()
                _isPlaying.value = true
                startProgressTracker()
            }
        } else {
            _currentTrack.value?.let { playTrack(it) }
        }
    }

    fun seekTo(positionMs: Long) {
        mediaPlayer?.let { mp ->
            val target = positionMs.coerceIn(0, _durationMs.value).toInt()
            mp.seekTo(target)
            _currentPositionMs.value = target.toLong()
        }
    }

    fun skipNext() {
        if (queue.isEmpty()) return
        if (_isShuffleEnabled.value) {
            queueIndex = (queue.indices).random()
        } else {
            queueIndex = (queueIndex + 1) % queue.size
        }
        playTrack(queue[queueIndex])
    }

    fun skipPrevious() {
        if (queue.isEmpty()) return
        // If > 3 seconds in, restart track
        if (_currentPositionMs.value > 3000) {
            seekTo(0)
            return
        }
        queueIndex = if (queueIndex - 1 < 0) queue.size - 1 else queueIndex - 1
        playTrack(queue[queueIndex])
    }

    fun toggleShuffle() {
        _isShuffleEnabled.value = !_isShuffleEnabled.value
    }

    fun toggleRepeat() {
        _repeatMode.value = when (_repeatMode.value) {
            RepeatMode.OFF -> RepeatMode.ALL
            RepeatMode.ALL -> RepeatMode.ONE
            RepeatMode.ONE -> RepeatMode.OFF
        }
    }

    private fun handleTrackCompletion() {
        when (_repeatMode.value) {
            RepeatMode.ONE -> {
                seekTo(0)
                mediaPlayer?.start()
                _isPlaying.value = true
            }
            RepeatMode.ALL -> {
                skipNext()
            }
            RepeatMode.OFF -> {
                if (queueIndex < queue.size - 1) {
                    skipNext()
                } else {
                    _isPlaying.value = false
                    seekTo(0)
                }
            }
        }
    }

    private fun startProgressTracker() {
        stopProgressTracker()
        progressJob = scope.launch {
            while (isActive && _isPlaying.value) {
                mediaPlayer?.let { mp ->
                    if (mp.isPlaying) {
                        _currentPositionMs.value = mp.currentPosition.toLong()
                    }
                }
                delay(250)
            }
        }
    }

    private fun stopProgressTracker() {
        progressJob?.cancel()
        progressJob = null
    }

    // --- Offline Download Functionality ---
    fun downloadTrack(track: Track, onComplete: () -> Unit = {}) {
        scope.launch(Dispatchers.IO) {
            try {
                _downloadProgress.value = _downloadProgress.value + (track.id to 0.1f)
                val targetFile = AudioSynthesizer.generateDownloadFile(
                    context = context,
                    trackId = track.id,
                    genre = track.genre,
                    durationSeconds = (track.durationMs / 1000).toInt().coerceAtLeast(60)
                ) { progress ->
                    _downloadProgress.value = _downloadProgress.value + (track.id to progress)
                }

                // Save to Room DB
                val entity = DownloadedTrackEntity(
                    trackId = track.id,
                    title = track.title,
                    artist = track.artist,
                    album = track.album,
                    durationMs = track.durationMs,
                    localFilePath = targetFile.absolutePath,
                    fileSizeBytes = targetFile.length(),
                    genre = track.genre
                )
                db.musicDao().insertDownload(entity)

                // Remove from progress map
                val updated = _downloadProgress.value.toMutableMap()
                updated.remove(track.id)
                _downloadProgress.value = updated

                launch(Dispatchers.Main) {
                    onComplete()
                }
            } catch (e: Exception) {
                Log.e(tag, "Failed to download track ${track.id}", e)
                val updated = _downloadProgress.value.toMutableMap()
                updated.remove(track.id)
                _downloadProgress.value = updated
            }
        }
    }

    fun removeDownload(trackId: String) {
        scope.launch(Dispatchers.IO) {
            try {
                val entity = db.musicDao().getDownload(trackId)
                if (entity != null) {
                    val file = File(entity.localFilePath)
                    if (file.exists()) file.delete()
                    db.musicDao().deleteDownload(trackId)
                }
            } catch (e: Exception) {
                Log.e(tag, "Failed to remove download", e)
            }
        }
    }

    // --- Equalizer & Audio Effects ---
    private fun attachAudioEffects(audioSessionId: Int) {
        try {
            equalizerFx = Equalizer(0, audioSessionId).apply {
                enabled = _equalizerState.value.isEnabled
            }
            bassBoostFx = BassBoost(0, audioSessionId).apply {
                enabled = _equalizerState.value.isEnabled
                setStrength((_equalizerState.value.bassBoostLevel * 10).toInt().toShort())
            }
            virtualizerFx = Virtualizer(0, audioSessionId).apply {
                enabled = _equalizerState.value.isEnabled
                setStrength((_equalizerState.value.virtualizerLevel * 10).toInt().toShort())
            }
            applyEqualizerGains()
        } catch (e: Exception) {
            Log.w(tag, "Hardware audio effects unavailable: ${e.message}")
        }
    }

    private fun releaseAudioEffects() {
        try {
            equalizerFx?.release()
            bassBoostFx?.release()
            virtualizerFx?.release()
        } catch (ignored: Exception) {}
        equalizerFx = null
        bassBoostFx = null
        virtualizerFx = null
    }

    fun setEqualizerBandGain(bandIndex: Int, gainDb: Float) {
        val current = _equalizerState.value
        val updatedBands = current.bands.map { band ->
            if (band.index == bandIndex) band.copy(gainDb = gainDb) else band
        }
        _equalizerState.value = current.copy(
            bands = updatedBands,
            currentPreset = "Custom"
        )
        applyEqualizerGains()
    }

    fun setBassBoost(level: Float) {
        _equalizerState.value = _equalizerState.value.copy(bassBoostLevel = level)
        try {
            bassBoostFx?.setStrength((level * 10).toInt().toShort())
        } catch (ignored: Exception) {}
    }

    fun setVirtualizer(level: Float) {
        _equalizerState.value = _equalizerState.value.copy(virtualizerLevel = level)
        try {
            virtualizerFx?.setStrength((level * 10).toInt().toShort())
        } catch (ignored: Exception) {}
    }

    fun setEqualizerPreset(presetName: String) {
        val preset = EqualizerState.PRESETS.find { it.name == presetName } ?: return
        val current = _equalizerState.value
        val updatedBands = current.bands.mapIndexed { idx, band ->
            band.copy(gainDb = preset.gains.getOrElse(idx) { 0f })
        }
        _equalizerState.value = current.copy(
            bands = updatedBands,
            bassBoostLevel = preset.bassBoost,
            virtualizerLevel = preset.virtualizer,
            currentPreset = presetName
        )
        applyEqualizerGains()
        try {
            bassBoostFx?.setStrength((preset.bassBoost * 10).toInt().toShort())
            virtualizerFx?.setStrength((preset.virtualizer * 10).toInt().toShort())
        } catch (ignored: Exception) {}
    }

    fun toggleEqualizerEnabled() {
        val newState = !_equalizerState.value.isEnabled
        _equalizerState.value = _equalizerState.value.copy(isEnabled = newState)
        try {
            equalizerFx?.enabled = newState
            bassBoostFx?.enabled = newState
            virtualizerFx?.enabled = newState
        } catch (ignored: Exception) {}
    }

    private fun applyEqualizerGains() {
        val eq = equalizerFx ?: return
        try {
            val numBands = eq.numberOfBands.toInt()
            val minRange = eq.bandLevelRange[0]
            val maxRange = eq.bandLevelRange[1]

            _equalizerState.value.bands.forEachIndexed { i, band ->
                if (i < numBands) {
                    val targetMilliBels = (band.gainDb * 100).toInt()
                        .coerceIn(minRange.toInt(), maxRange.toInt()).toShort()
                    eq.setBandLevel(i.toShort(), targetMilliBels)
                }
            }
        } catch (e: Exception) {
            Log.w(tag, "Could not set band level: ${e.message}")
        }
    }

    // --- Device Local Music Scanner ---
    suspend fun scanLocalDeviceAudio(): List<Track> {
        val localTracks = mutableListOf<Track>()
        try {
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE
            )
            // Query without strict is_music flag to catch all playable device audio
            val selection = "${MediaStore.Audio.Media.DURATION} >= ?"
            val selectionArgs = arrayOf("3000") // 3+ seconds

            val cursor = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                "${MediaStore.Audio.Media.TITLE} ASC"
            )

            cursor?.use {
                val idCol = it.getColumnIndex(MediaStore.Audio.Media._ID)
                val titleCol = it.getColumnIndex(MediaStore.Audio.Media.TITLE)
                val artistCol = it.getColumnIndex(MediaStore.Audio.Media.ARTIST)
                val albumCol = it.getColumnIndex(MediaStore.Audio.Media.ALBUM)
                val durCol = it.getColumnIndex(MediaStore.Audio.Media.DURATION)
                val sizeCol = it.getColumnIndex(MediaStore.Audio.Media.SIZE)

                while (it.moveToNext()) {
                    val id = if (idCol >= 0) it.getLong(idCol) else continue
                    val title = if (titleCol >= 0) it.getString(titleCol) ?: "Unknown Track" else "Unknown Track"
                    val artist = if (artistCol >= 0) it.getString(artistCol) ?: "Unknown Artist" else "Unknown Artist"
                    val album = if (albumCol >= 0) it.getString(albumCol) ?: "Local Audio" else "Local Audio"
                    val duration = if (durCol >= 0) it.getLong(durCol) else 0L
                    val fileSize = if (sizeCol >= 0) it.getLong(sizeCol) else 0L

                    val contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

                    localTracks.add(
                        Track(
                            id = "local_$id",
                            title = title,
                            artist = if (artist.equals("<unknown>", ignoreCase = true)) "Unknown Artist" else artist,
                            album = if (album.equals("<unknown>", ignoreCase = true)) "Local Audio" else album,
                            durationMs = if (duration > 0) duration else 180000L,
                            localFilePath = contentUri.toString(),
                            isDownloaded = true,
                            fileSizeBytes = fileSize,
                            genre = "Local Device",
                            coverGradientStart = 0xFF6366F1,
                            coverGradientEnd = 0xFF14B8A6,
                            iconCategory = "phone",
                            audioFormat = "Device Audio",
                            isLocalDeviceTrack = true
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Log.w(tag, "Error querying MediaStore: ${e.message}")
        }
        return localTracks
    }

    // Import audio files selected from SAF file picker
    suspend fun importAudioFilesFromUris(uris: List<Uri>): List<Track> {
        val imported = mutableListOf<Track>()
        for (uri in uris) {
            try {
                var title = "Audio File"
                var size = 0L

                val cursor = context.contentResolver.query(uri, null, null, null, null)
                cursor?.use {
                    if (it.moveToFirst()) {
                        val nameCol = it.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                        val sizeCol = it.getColumnIndex(android.provider.OpenableColumns.SIZE)
                        if (nameCol >= 0) {
                            title = it.getString(nameCol) ?: "Audio File"
                        }
                        if (sizeCol >= 0) {
                            size = it.getLong(sizeCol)
                        }
                    }
                }

                // Strip extension for title display
                val cleanTitle = title.substringBeforeLast(".")
                val trackId = "imported_${System.currentTimeMillis()}_${imported.size}"

                imported.add(
                    Track(
                        id = trackId,
                        title = cleanTitle,
                        artist = "Imported Audio",
                        album = "My Files",
                        durationMs = 180000L, // will update on playback
                        localFilePath = uri.toString(),
                        isDownloaded = true,
                        fileSizeBytes = size,
                        genre = "Local Device",
                        coverGradientStart = 0xFF06B6D4,
                        coverGradientEnd = 0xFF3B82F6,
                        iconCategory = "phone",
                        audioFormat = "Device Audio",
                        isLocalDeviceTrack = true
                    )
                )
            } catch (e: Exception) {
                Log.w(tag, "Failed to import URI: $uri", e)
            }
        }
        return imported
    }

    fun release() {
        stopProgressTracker()
        releaseAudioEffects()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
