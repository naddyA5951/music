package com.example.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.MusicPlayerManager
import com.example.audio.RepeatMode
import com.example.data.MusicCatalog
import com.example.data.local.FavoriteTrackEntity
import com.example.data.local.MusicDatabase
import com.example.data.local.PlaylistEntity
import com.example.model.EqualizerState
import com.example.model.Playlist
import com.example.model.Track
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

enum class AppNavTab {
    HOME, SEARCH, LIBRARY, EQUALIZER
}

enum class StreamingQuality(val label: String, val bitrate: String) {
    LOSSLESS("Audiophile FLAC", "1411 kbps / 96kHz"),
    HIGH("Master Studio", "320 kbps / 48kHz"),
    BALANCED("Standard Hi-Fi", "192 kbps / 44.1kHz"),
    DATA_SAVER("Data Saver", "96 kbps AAC")
}

class MusicViewModel(application: Application) : AndroidViewModel(application) {

    val playerManager = MusicPlayerManager(application)
    private val db = MusicDatabase.getDatabase(application)

    // Active Navigation Tab
    private val _currentTab = MutableStateFlow(AppNavTab.HOME)
    val currentTab: StateFlow<AppNavTab> = _currentTab.asStateFlow()

    // Offline Mode Filter Toggle
    private val _isOfflineOnlyMode = MutableStateFlow(false)
    val isOfflineOnlyMode: StateFlow<Boolean> = _isOfflineOnlyMode.asStateFlow()

    // Streaming Quality
    private val _streamingQuality = MutableStateFlow(StreamingQuality.HIGH)
    val streamingQuality: StateFlow<StreamingQuality> = _streamingQuality.asStateFlow()

    // Dark Mode Toggle (Default True)
    private val _isDarkMode = MutableStateFlow(true)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    // Fullscreen Now Playing Sheet
    private val _isNowPlayingExpanded = MutableStateFlow(false)
    val isNowPlayingExpanded: StateFlow<Boolean> = _isNowPlayingExpanded.asStateFlow()

    // Show Lyrics View in Now Playing
    private val _showLyrics = MutableStateFlow(false)
    val showLyrics: StateFlow<Boolean> = _showLyrics.asStateFlow()

    // Dialog States
    private val _showCreatePlaylistDialog = MutableStateFlow(false)
    val showCreatePlaylistDialog: StateFlow<Boolean> = _showCreatePlaylistDialog.asStateFlow()

    private val _showAddToPlaylistTrack = MutableStateFlow<Track?>(null)
    val showAddToPlaylistTrack: StateFlow<Track?> = _showAddToPlaylistTrack.asStateFlow()

    private val _showShareDialogTrack = MutableStateFlow<Track?>(null)
    val showShareDialogTrack: StateFlow<Track?> = _showShareDialogTrack.asStateFlow()

    private val _showProfileSyncDialog = MutableStateFlow(false)
    val showProfileSyncDialog: StateFlow<Boolean> = _showProfileSyncDialog.asStateFlow()

    private val _showAudioQualityDialog = MutableStateFlow(false)
    val showAudioQualityDialog: StateFlow<Boolean> = _showAudioQualityDialog.asStateFlow()

    private val _selectedPlaylist = MutableStateFlow<Playlist?>(null)
    val selectedPlaylist: StateFlow<Playlist?> = _selectedPlaylist.asStateFlow()

    // Search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedGenreFilter = MutableStateFlow<String?>(null)
    val selectedGenreFilter: StateFlow<String?> = _selectedGenreFilter.asStateFlow()

    // User Profile
    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    // Local device tracks
    private val _localDeviceTracks = MutableStateFlow<List<Track>>(emptyList())
    val localDeviceTracks: StateFlow<List<Track>> = _localDeviceTracks.asStateFlow()

    private val _isScanningDevice = MutableStateFlow(false)
    val isScanningDevice: StateFlow<Boolean> = _isScanningDevice.asStateFlow()

    private val _scanMessage = MutableStateFlow<String?>(null)
    val scanMessage: StateFlow<String?> = _scanMessage.asStateFlow()

    // Base Catalog
    private val _baseCatalog = MutableStateFlow(MusicCatalog.sampleTracks)

    // DB flows
    private val _dbDownloads = db.musicDao().getAllDownloads()
    private val _dbFavorites = db.musicDao().getAllFavorites()
    val dbPlaylists = db.musicDao().getAllPlaylists()

    // Enriched Tracks with favorite and downloaded flags
    val allTracks: StateFlow<List<Track>> = combine(
        _baseCatalog,
        _localDeviceTracks,
        _dbDownloads,
        _dbFavorites
    ) { base, local, downloads, favorites ->
        val downloadedMap = downloads.associateBy { it.trackId }
        val favoriteSet = favorites.map { it.trackId }.toSet()

        val allCombined = (base + local).distinctBy { it.id }

        allCombined.map { track ->
            val dl = downloadedMap[track.id]
            track.copy(
                isDownloaded = dl != null || track.isLocalDeviceTrack,
                localFilePath = dl?.localFilePath ?: track.localFilePath,
                fileSizeBytes = dl?.fileSizeBytes ?: track.fileSizeBytes,
                isFavorite = favoriteSet.contains(track.id)
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, MusicCatalog.sampleTracks)

    // Filtered Tracks based on search and offline mode
    val filteredTracks: StateFlow<List<Track>> = combine(
        allTracks,
        _searchQuery,
        _selectedGenreFilter,
        _isOfflineOnlyMode
    ) { tracks, query, genre, offlineOnly ->
        tracks.filter { track ->
            val matchesQuery = query.isBlank() ||
                track.title.contains(query, ignoreCase = true) ||
                track.artist.contains(query, ignoreCase = true) ||
                track.album.contains(query, ignoreCase = true)

            val matchesGenre = genre == null ||
                track.genre.equals(genre, ignoreCase = true) ||
                track.artist.contains(genre, ignoreCase = true)
            val matchesOffline = !offlineOnly || track.isDownloaded

            matchesQuery && matchesGenre && matchesOffline
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Player state delegates
    val currentTrack = playerManager.currentTrack
    val isPlaying = playerManager.isPlaying
    val currentPositionMs = playerManager.currentPositionMs
    val durationMs = playerManager.durationMs
    val isBuffering = playerManager.isBuffering
    val isShuffleEnabled = playerManager.isShuffleEnabled
    val repeatMode = playerManager.repeatMode
    val equalizerState = playerManager.equalizerState
    val downloadProgress = playerManager.downloadProgress

    init {
        seedInitialPlaylists()
    }

    private fun seedInitialPlaylists() {
        viewModelScope.launch {
            db.musicDao().getAllPlaylists().collect { existing ->
                if (existing.isEmpty()) {
                    MusicCatalog.defaultPlaylists.forEachIndexed { index, pair ->
                        val initialTracks = when (index) {
                            0 -> listOf("nfak_1", "nfak_2", "nfak_3", "nfak_4", "nfak_5", "nfak_afreen", "nfak_6", "nfak_7", "nfak_8", "nfak_9", "nfak_10", "nfak_11")
                            1 -> listOf("arijit_1", "arijit_2", "arijit_3", "arijit_4", "arijit_5", "arijit_6", "arijit_7", "arijit_8", "arijit_9", "arijit_10", "arijit_15")
                            2 -> listOf("anjum_1", "anjum_2", "anjum_3", "anjum_4", "anjum_5", "anjum_6", "anjum_7", "anjum_10", "anjum_11", "anjum_12", "anjum_13")
                            3 -> listOf("asim_1", "asim_2", "asim_3", "asim_4", "asim_5", "asim_6", "asim_8", "asim_10", "asim_11", "track_34")
                            4 -> listOf("bilal_1", "bilal_2", "bilal_3", "bilal_4", "bilal_5", "bilal_6", "bilal_8", "bilal_10", "bilal_11")
                            5 -> listOf("track_23", "track_24", "track_25", "track_26", "track_27", "track_28", "track_29", "track_30", "track_31", "track_33", "track_35")
                            6 -> listOf("nfak_11", "arijit_1", "anjum_1", "asim_1", "bilal_2", "track_23")
                            7 -> listOf("nfak_1", "nfak_5", "arijit_6", "track_5", "track_13", "track_22")
                            else -> listOf("anjum_4", "anjum_9", "track_2", "track_19", "track_21")
                        }
                        val entity = PlaylistEntity(
                            id = "playlist_${index + 1}",
                            title = pair.first,
                            description = pair.second,
                            trackIdsJson = initialTracks.joinToString(","),
                            emoji = when (index) {
                                0 -> "🕊️"
                                1 -> "💔"
                                2 -> "🎤"
                                3 -> "✨"
                                4 -> "🥁"
                                5 -> "🇵🇰"
                                6 -> "❤️"
                                7 -> "🎧"
                                else -> "🌙"
                            },
                            isCustom = index > 1,
                            gradientStart = when (index) {
                                0 -> 0xFF78350F
                                1 -> 0xFFDC2626
                                2 -> 0xFF18181B
                                3 -> 0xFF7C3AED
                                4 -> 0xFFD97706
                                5 -> 0xFF047857
                                6 -> 0xFFF43F5E
                                7 -> 0xFF8B5CF6
                                else -> 0xFF06B6D4
                            },
                            gradientEnd = when (index) {
                                0 -> 0xFF1E1B4B
                                1 -> 0xFF7C2D12
                                2 -> 0xFF450A0A
                                3 -> 0xFFDB2777
                                4 -> 0xFF78350F
                                5 -> 0xFF065F46
                                6 -> 0xFFBE185D
                                7 -> 0xFF4338CA
                                else -> 0xFF0E7490
                            }
                        )
                        db.musicDao().insertPlaylist(entity)
                    }
                }
            }
        }
    }

    fun selectTab(tab: AppNavTab) {
        _currentTab.value = tab
    }

    fun toggleOfflineOnlyMode() {
        _isOfflineOnlyMode.value = !_isOfflineOnlyMode.value
    }

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }

    fun setStreamingQuality(quality: StreamingQuality) {
        _streamingQuality.value = quality
        _showAudioQualityDialog.value = false
    }

    fun setNowPlayingExpanded(expanded: Boolean) {
        _isNowPlayingExpanded.value = expanded
    }

    fun toggleShowLyrics() {
        _showLyrics.value = !_showLyrics.value
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setGenreFilter(genre: String?) {
        _selectedGenreFilter.value = if (_selectedGenreFilter.value == genre) null else genre
    }

    fun setSelectedPlaylist(playlist: Playlist?) {
        _selectedPlaylist.value = playlist
    }

    // --- Playback triggers ---
    fun playTrack(track: Track) {
        val tracks = allTracks.value
        val index = tracks.indexOfFirst { it.id == track.id }.coerceAtLeast(0)
        playerManager.setQueue(tracks, index, autoPlay = true)
    }

    fun playPlaylist(playlist: Playlist, tracks: List<Track>) {
        if (tracks.isEmpty()) return
        playerManager.setQueue(tracks, 0, autoPlay = true)
    }

    fun togglePlayPause() = playerManager.togglePlayPause()
    fun seekTo(positionMs: Long) = playerManager.seekTo(positionMs)
    fun skipNext() = playerManager.skipNext()
    fun skipPrevious() = playerManager.skipPrevious()
    fun toggleShuffle() = playerManager.toggleShuffle()
    fun toggleRepeat() = playerManager.toggleRepeat()

    // --- Favorite Toggle ---
    fun toggleFavorite(track: Track) {
        viewModelScope.launch {
            if (track.isFavorite) {
                db.musicDao().deleteFavorite(track.id)
            } else {
                db.musicDao().insertFavorite(FavoriteTrackEntity(track.id))
            }
        }
    }

    // --- Download Toggle ---
    fun downloadTrack(track: Track) {
        if (track.isDownloaded) {
            playerManager.removeDownload(track.id)
        } else {
            playerManager.downloadTrack(track)
        }
    }

    // --- Equalizer Controls ---
    fun setEqualizerBandGain(bandIndex: Int, gainDb: Float) =
        playerManager.setEqualizerBandGain(bandIndex, gainDb)

    fun setBassBoost(level: Float) = playerManager.setBassBoost(level)
    fun setVirtualizer(level: Float) = playerManager.setVirtualizer(level)
    fun setEqualizerPreset(presetName: String) = playerManager.setEqualizerPreset(presetName)
    fun toggleEqualizerEnabled() = playerManager.toggleEqualizerEnabled()

    // --- Playlist Management ---
    fun openCreatePlaylistDialog() {
        _showCreatePlaylistDialog.value = true
    }

    fun closeCreatePlaylistDialog() {
        _showCreatePlaylistDialog.value = false
    }

    fun createPlaylist(title: String, description: String, emoji: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            val entity = PlaylistEntity(
                id = UUID.randomUUID().toString(),
                title = title.trim(),
                description = description.trim(),
                trackIdsJson = "",
                emoji = emoji.ifBlank { "🎵" },
                isCustom = true,
                gradientStart = 0xFF8B5CF6,
                gradientEnd = 0xFFEC4899
            )
            db.musicDao().insertPlaylist(entity)
            _showCreatePlaylistDialog.value = false
        }
    }

    fun deletePlaylist(id: String) {
        viewModelScope.launch {
            db.musicDao().deletePlaylist(id)
            if (_selectedPlaylist.value?.id == id) {
                _selectedPlaylist.value = null
            }
        }
    }

    fun openAddToPlaylist(track: Track) {
        _showAddToPlaylistTrack.value = track
    }

    fun closeAddToPlaylist() {
        _showAddToPlaylistTrack.value = null
    }

    fun addTrackToPlaylist(playlistEntity: PlaylistEntity, trackId: String) {
        viewModelScope.launch {
            val currentTracks = playlistEntity.trackIdsJson.split(",")
                .filter { it.isNotBlank() }
                .toMutableList()
            if (!currentTracks.contains(trackId)) {
                currentTracks.add(trackId)
                db.musicDao().updatePlaylist(
                    playlistEntity.copy(trackIdsJson = currentTracks.joinToString(","))
                )
            }
            _showAddToPlaylistTrack.value = null
        }
    }

    fun removeTrackFromPlaylist(playlist: Playlist, trackId: String) {
        viewModelScope.launch {
            val updated = playlist.trackIds.filter { it != trackId }
            val entity = PlaylistEntity(
                id = playlist.id,
                title = playlist.title,
                description = playlist.description,
                trackIdsJson = updated.joinToString(","),
                emoji = playlist.emoji,
                isCustom = playlist.isCustom,
                gradientStart = playlist.gradientStart,
                gradientEnd = playlist.gradientEnd,
                createdAt = playlist.createdAt
            )
            db.musicDao().updatePlaylist(entity)
            _selectedPlaylist.value = playlist.copy(trackIds = updated)
        }
    }

    // --- Social Sharing ---
    fun openShareDialog(track: Track) {
        _showShareDialogTrack.value = track
    }

    fun closeShareDialog() {
        _showShareDialogTrack.value = null
    }

    fun shareTrackExternally(context: Context, track: Track, quoteText: String? = null) {
        val shareText = buildString {
            append("🎵 Listening to \"${track.title}\" by ${track.artist} on Music Libreriya\n")
            append("Album: ${track.album} • Hi-Res Audio\n")
            if (!quoteText.isNullOrBlank()) {
                append("\n\"$quoteText\"\n")
            }
            append("\nStream & Offline in high fidelity on Music Libreriya: https://musiclibreriya.app/track/${track.id}")
        }

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Track via Music Libreriya")
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(shareIntent)
    }

    // --- Device Music Scanner ---
    fun scanDeviceAudio() {
        viewModelScope.launch {
            _isScanningDevice.value = true
            _scanMessage.value = "Scanning phone storage for music..."
            try {
                val scanned = playerManager.scanLocalDeviceAudio()
                _localDeviceTracks.value = scanned
                _scanMessage.value = if (scanned.isEmpty()) {
                    "No audio files found on device storage. You can also pick audio files directly using 'Choose Files'."
                } else {
                    "Found ${scanned.size} audio track(s) on your phone!"
                }
            } catch (e: Exception) {
                _scanMessage.value = "Scan error: ${e.message}"
            } finally {
                _isScanningDevice.value = false
            }
        }
    }

    fun importDeviceAudioFiles(uris: List<Uri>) {
        if (uris.isEmpty()) return
        viewModelScope.launch {
            _isScanningDevice.value = true
            _scanMessage.value = "Importing ${uris.size} audio file(s)..."
            try {
                val imported = playerManager.importAudioFilesFromUris(uris)
                val current = _localDeviceTracks.value.toMutableList()
                val existingPaths = current.map { it.localFilePath }.toSet()
                val newItems = imported.filter { !existingPaths.contains(it.localFilePath) }
                current.addAll(0, newItems)
                _localDeviceTracks.value = current
                _scanMessage.value = "Successfully imported ${newItems.size} track(s) into your library!"
            } catch (e: Exception) {
                _scanMessage.value = "Import error: ${e.message}"
            } finally {
                _isScanningDevice.value = false
            }
        }
    }

    fun clearScanMessage() {
        _scanMessage.value = null
    }

    // --- Cross-Platform Sync Export / Import ---
    fun openProfileSyncDialog() {
        _showProfileSyncDialog.value = true
    }

    fun closeProfileSyncDialog() {
        _showProfileSyncDialog.value = false
    }

    fun openAudioQualityDialog() {
        _showAudioQualityDialog.value = true
    }

    fun closeAudioQualityDialog() {
        _showAudioQualityDialog.value = false
    }

    suspend fun generateExportSyncJson(): String {
        val obj = JSONObject()
        obj.put("version", "1.0")
        obj.put("appName", "Music Libreriya")
        obj.put("deviceId", _userProfile.value.syncDeviceId)
        obj.put("exportTime", System.currentTimeMillis())

        val favs = db.musicDao().getAllFavorites()
        val favArray = JSONArray()
        // emit once
        db.musicDao().getAllPlaylists().collect { plList ->
            val plArray = JSONArray()
            plList.forEach { pl ->
                val plObj = JSONObject().apply {
                    put("id", pl.id)
                    put("title", pl.title)
                    put("description", pl.description)
                    put("trackIds", pl.trackIdsJson)
                    put("emoji", pl.emoji)
                }
                plArray.put(plObj)
            }
            obj.put("playlists", plArray)
            return@collect
        }
        return obj.toString(2)
    }

    fun importSyncJson(jsonString: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val obj = JSONObject(jsonString)
                val playlistsArr = obj.optJSONArray("playlists")
                if (playlistsArr != null) {
                    for (i in 0 until playlistsArr.length()) {
                        val pl = playlistsArr.getJSONObject(i)
                        val entity = PlaylistEntity(
                            id = pl.optString("id", UUID.randomUUID().toString()),
                            title = pl.optString("title", "Synced Playlist"),
                            description = pl.optString("description", "Imported from other device"),
                            trackIdsJson = pl.optString("trackIds", ""),
                            emoji = pl.optString("emoji", "🎵"),
                            isCustom = true,
                            gradientStart = 0xFF8B5CF6,
                            gradientEnd = 0xFF3B82F6
                        )
                        db.musicDao().insertPlaylist(entity)
                    }
                }
                _userProfile.value = _userProfile.value.copy(
                    lastSyncTimestamp = System.currentTimeMillis()
                )
                onResult(true, "Successfully synced ${playlistsArr?.length() ?: 0} playlists!")
            } catch (e: Exception) {
                onResult(false, "Invalid sync data: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        playerManager.release()
    }
}
