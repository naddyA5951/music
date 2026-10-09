package com.example.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.PlaylistEntity
import com.example.model.Playlist
import com.example.model.Track
import com.example.ui.theme.CyanAura
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.SunsetCoral

@Composable
fun LibraryScreen(
    playlists: List<PlaylistEntity>,
    allTracks: List<Track>,
    selectedPlaylist: Playlist?,
    currentTrack: Track?,
    isPlaying: Boolean,
    downloadProgress: Map<String, Float>,
    onSelectPlaylist: (Playlist?) -> Unit,
    onCreatePlaylistClick: () -> Unit,
    onDeletePlaylist: (String) -> Unit,
    onTrackClick: (Track) -> Unit,
    onFavoriteToggle: (Track) -> Unit,
    onDownloadClick: (Track) -> Unit,
    onAddToPlaylistClick: (Track) -> Unit,
    onRemoveFromPlaylist: (Playlist, String) -> Unit,
    onShareClick: (Track) -> Unit,
    onScanDeviceAudio: () -> Unit,
    onImportAudioFiles: (List<Uri>) -> Unit = {},
    isScanningDevice: Boolean = false,
    scanMessage: String? = null,
    onClearScanMessage: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    if (selectedPlaylist != null) {
        // Handle Back navigation to library list
        BackHandler {
            onSelectPlaylist(null)
        }

        PlaylistDetailView(
            playlist = selectedPlaylist,
            allTracks = allTracks,
            currentTrack = currentTrack,
            isPlaying = isPlaying,
            downloadProgress = downloadProgress,
            onBackClick = { onSelectPlaylist(null) },
            onTrackClick = onTrackClick,
            onFavoriteToggle = onFavoriteToggle,
            onDownloadClick = onDownloadClick,
            onAddToPlaylistClick = onAddToPlaylistClick,
            onRemoveFromPlaylist = { trackId -> onRemoveFromPlaylist(selectedPlaylist, trackId) },
            onShareClick = onShareClick,
            modifier = modifier
        )
        return
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Playlists", "Offline Tracks", "Liked Songs", "Device Audio")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("library_screen")
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Library Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Your Music Library",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${playlists.size} playlists • ${allTracks.filter { it.isDownloaded }.size} offline",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Create Playlist FAB / Button
            Button(
                onClick = onCreatePlaylistClick,
                colors = ButtonDefaults.buttonColors(containerColor = ElectricViolet),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.testTag("create_playlist_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "New",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Custom TabRow
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = ElectricViolet,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = ElectricViolet
                )
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.sp
                            ),
                            color = if (selectedTabIndex == index) ElectricViolet else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        when (selectedTabIndex) {
            0 -> {
                // Playlists Tab
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(playlists, key = { it.id }) { plEntity ->
                        val trackIds = plEntity.trackIdsJson.split(",").filter { it.isNotBlank() }
                        val playlistModel = Playlist(
                            id = plEntity.id,
                            title = plEntity.title,
                            description = plEntity.description,
                            trackIds = trackIds,
                            emoji = plEntity.emoji,
                            isCustom = plEntity.isCustom,
                            gradientStart = plEntity.gradientStart,
                            gradientEnd = plEntity.gradientEnd,
                            createdAt = plEntity.createdAt
                        )

                        PlaylistItemCard(
                            playlist = playlistModel,
                            trackCount = trackIds.size,
                            onClick = { onSelectPlaylist(playlistModel) },
                            onDelete = if (plEntity.isCustom) {
                                { onDeletePlaylist(plEntity.id) }
                            } else null
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }

            1 -> {
                // Offline Downloads Tab
                val downloadedTracks = allTracks.filter { it.isDownloaded }
                val totalSizeBytes = downloadedTracks.sumOf { it.fileSizeBytes }
                val totalMb = totalSizeBytes / (1024f * 1024f)

                Column(modifier = Modifier.weight(1f)) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(CyanAura.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = null,
                                    tint = CyanAura,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = "Offline Storage Active",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${downloadedTracks.size} tracks cached • 100% playable without internet",
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (downloadedTracks.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "📥", fontSize = 40.sp)
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "No offline tracks yet",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Tap the download icon on any song to save it for offline playback.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(downloadedTracks, key = { it.id }) { track ->
                                TrackItemRow(
                                    track = track,
                                    isCurrentTrack = currentTrack?.id == track.id,
                                    isPlaying = isPlaying && currentTrack?.id == track.id,
                                    downloadProgress = downloadProgress[track.id],
                                    onTrackClick = { onTrackClick(track) },
                                    onFavoriteToggle = { onFavoriteToggle(track) },
                                    onDownloadClick = { onDownloadClick(track) },
                                    onAddToPlaylistClick = { onAddToPlaylistClick(track) },
                                    onShareClick = { onShareClick(track) }
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(80.dp))
                            }
                        }
                    }
                }
            }

            2 -> {
                // Liked Songs Tab
                val favoriteTracks = allTracks.filter { it.isFavorite }
                if (favoriteTracks.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "❤️", fontSize = 40.sp)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Your Liked Songs will appear here",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Tap the heart icon on any song to add to favorites.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(favoriteTracks, key = { it.id }) { track ->
                            TrackItemRow(
                                track = track,
                                isCurrentTrack = currentTrack?.id == track.id,
                               isPlaying = isPlaying && currentTrack?.id == track.id,
                                downloadProgress = downloadProgress[track.id],
                                onTrackClick = { onTrackClick(track) },
                                onFavoriteToggle = { onFavoriteToggle(track) },
                                onDownloadClick = { onDownloadClick(track) },
                                onAddToPlaylistClick = { onAddToPlaylistClick(track) },
                                onShareClick = { onShareClick(track) }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }

            3 -> {
                // Device Audio Tab
                val deviceTracks = allTracks.filter { it.isLocalDeviceTrack }

                val filePickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.OpenMultipleDocuments()
                ) { uris: List<Uri> ->
                    if (uris.isNotEmpty()) {
                        onImportAudioFiles(uris)
                    }
                }

                Column(modifier = Modifier.weight(1f)) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Add Songs from Phone",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Scan storage or select audio files directly (MP3, WAV, FLAC, M4A)",
                                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = onScanDeviceAudio,
                                    enabled = !isScanningDevice,
                                    colors = ButtonDefaults.buttonColors(containerColor = CyanAura),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    if (isScanningDevice) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(16.dp),
                                            color = Color.Black,
                                            strokeWidth = 2.dp
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(text = "Scanning...", color = Color.Black, fontWeight = FontWeight.Bold)
                                    } else {
                                        Text(text = "🔍 Scan Storage", color = Color.Black, fontWeight = FontWeight.Bold)
                                    }
                                }

                                OutlinedButton(
                                    onClick = {
                                        filePickerLauncher.launch(arrayOf("audio/*"))
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "📂 Choose Files",
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            if (!scanMessage.isNullOrBlank()) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = scanMessage,
                                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = "✕",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier
                                            .clickable { onClearScanMessage() }
                                            .padding(4.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (deviceTracks.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            ) {
                                Text(text = "🎵", fontSize = 44.sp)
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "No device audio loaded yet",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Tap 'Scan Storage' to automatically find music on your phone, or tap 'Choose Files' to select MP3s from any folder.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    lineHeight = 18.sp
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {
                                        filePickerLauncher.launch(arrayOf("audio/*"))
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = ElectricViolet),
                                    shape = RoundedCornerShape(14.dp)
                                ) {
                                    Text("Pick Songs from Files", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(deviceTracks, key = { it.id }) { track ->
                                TrackItemRow(
                                    track = track,
                                    isCurrentTrack = currentTrack?.id == track.id,
                                    isPlaying = isPlaying && currentTrack?.id == track.id,
                                    downloadProgress = downloadProgress[track.id],
                                    onTrackClick = { onTrackClick(track) },
                                    onFavoriteToggle = { onFavoriteToggle(track) },
                                    onDownloadClick = { onDownloadClick(track) },
                                    onAddToPlaylistClick = { onAddToPlaylistClick(track) },
                                    onShareClick = { onShareClick(track) }
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(80.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlaylistItemCard(
    playlist: Playlist,
    trackCount: Int,
    onClick: () -> Unit,
    onDelete: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(playlist.gradientStart), Color(playlist.gradientEnd))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = playlist.emoji, fontSize = 26.sp)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = playlist.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$trackCount songs • ${playlist.description}",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (onDelete != null) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Playlist",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PlaylistDetailView(
    playlist: Playlist,
    allTracks: List<Track>,
    currentTrack: Track?,
    isPlaying: Boolean,
    downloadProgress: Map<String, Float>,
    onBackClick: () -> Unit,
    onTrackClick: (Track) -> Unit,
    onFavoriteToggle: (Track) -> Unit,
    onDownloadClick: (Track) -> Unit,
    onAddToPlaylistClick: (Track) -> Unit,
    onRemoveFromPlaylist: (String) -> Unit,
    onShareClick: (Track) -> Unit,
    modifier: Modifier = Modifier
) {
    val playlistTracks = playlist.trackIds.mapNotNull { id -> allTracks.find { it.id == id } }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("playlist_detail_view")
    ) {
        // Back Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.testTag("back_to_library")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = "Back to Library",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Playlist Hero Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(playlist.gradientStart),
                                Color(playlist.gradientEnd)
                            )
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = playlist.emoji, fontSize = 48.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = playlist.title,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 22.sp
                            ),
                            color = Color.White
                        )
                        Text(
                            text = "${playlistTracks.size} tracks • ${playlist.description}",
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Play All and Shuffle All
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = { playlistTracks.firstOrNull()?.let { onTrackClick(it) } },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = ElectricViolet),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.PlayArrow, null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Play All", fontWeight = FontWeight.Bold, color = Color.White)
            }

            OutlinedButton(
                onClick = { playlistTracks.shuffled().firstOrNull()?.let { onTrackClick(it) } },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Shuffle, null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Shuffle", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (playlistTracks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "This playlist is empty. Add songs using the '+' menu on any track!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(playlistTracks, key = { it.id }) { track ->
                    TrackItemRow(
                        track = track,
                        isCurrentTrack = currentTrack?.id == track.id,
                        isPlaying = isPlaying && currentTrack?.id == track.id,
                        downloadProgress = downloadProgress[track.id],
                        onTrackClick = { onTrackClick(track) },
                        onFavoriteToggle = { onFavoriteToggle(track) },
                        onDownloadClick = { onDownloadClick(track) },
                        onAddToPlaylistClick = { onAddToPlaylistClick(track) },
                        onShareClick = { onShareClick(track) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}
