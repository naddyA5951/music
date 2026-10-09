package com.example

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.GraphicEq
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.Playlist
import com.example.model.Track
import com.example.ui.components.AddToPlaylistDialog
import com.example.ui.components.AudioQualityDialog
import com.example.ui.components.CreatePlaylistDialog
import com.example.ui.components.EqualizerScreen
import com.example.ui.components.HomeScreen
import com.example.ui.components.LibraryScreen
import com.example.ui.components.MiniPlayer
import com.example.ui.components.NowPlayingSheet
import com.example.ui.components.ProfileSyncDialog
import com.example.ui.components.SearchScreen
import com.example.ui.components.ShareTrackDialog
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.MusicLibreriyaTheme
import com.example.viewmodel.AppNavTab
import com.example.viewmodel.MusicViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MusicViewModel = viewModel()
            val isDarkMode by viewModel.isDarkMode.collectAsStateWithLifecycle()

            MusicLibreriyaTheme(darkTheme = isDarkMode) {
                MusicLibreriyaApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MusicLibreriyaApp(viewModel: MusicViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Collect UI state
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val isOfflineOnlyMode by viewModel.isOfflineOnlyMode.collectAsStateWithLifecycle()
    val streamingQuality by viewModel.streamingQuality.collectAsStateWithLifecycle()
    val isNowPlayingExpanded by viewModel.isNowPlayingExpanded.collectAsStateWithLifecycle()
    val showLyrics by viewModel.showLyrics.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedGenreFilter by viewModel.selectedGenreFilter.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()

    val allTracks by viewModel.allTracks.collectAsStateWithLifecycle()
    val filteredTracks by viewModel.filteredTracks.collectAsStateWithLifecycle()
    val playlists by viewModel.dbPlaylists.collectAsStateWithLifecycle(initialValue = emptyList())
    val selectedPlaylist by viewModel.selectedPlaylist.collectAsStateWithLifecycle()

    // Player state
    val currentTrack by viewModel.currentTrack.collectAsStateWithLifecycle()
    val isPlaying by viewModel.isPlaying.collectAsStateWithLifecycle()
    val currentPositionMs by viewModel.currentPositionMs.collectAsStateWithLifecycle()
    val durationMs by viewModel.durationMs.collectAsStateWithLifecycle()
    val isBuffering by viewModel.isBuffering.collectAsStateWithLifecycle()
    val isShuffleEnabled by viewModel.isShuffleEnabled.collectAsStateWithLifecycle()
    val repeatMode by viewModel.repeatMode.collectAsStateWithLifecycle()
    val equalizerState by viewModel.equalizerState.collectAsStateWithLifecycle()
    val downloadProgress by viewModel.downloadProgress.collectAsStateWithLifecycle()

    // Dialog state
    val showCreatePlaylistDialog by viewModel.showCreatePlaylistDialog.collectAsStateWithLifecycle()
    val showAddToPlaylistTrack by viewModel.showAddToPlaylistTrack.collectAsStateWithLifecycle()
    val showShareDialogTrack by viewModel.showShareDialogTrack.collectAsStateWithLifecycle()
    val showProfileSyncDialog by viewModel.showProfileSyncDialog.collectAsStateWithLifecycle()
    val showAudioQualityDialog by viewModel.showAudioQualityDialog.collectAsStateWithLifecycle()

    val isScanningDevice by viewModel.isScanningDevice.collectAsStateWithLifecycle()
    val scanMessage by viewModel.scanMessage.collectAsStateWithLifecycle()

    // Runtime permission launcher for scanning audio
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.scanDeviceAudio()
        } else {
            viewModel.scanDeviceAudio() // will still attempt and fallback gracefully
        }
    }

    val requestScanWithPermission = {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_AUDIO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        val isGranted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        if (isGranted) {
            viewModel.scanDeviceAudio()
        } else {
            permissionLauncher.launch(permission)
        }
    }

    // Handle system back navigation
    BackHandler(enabled = isNowPlayingExpanded) {
        viewModel.setNowPlayingExpanded(false)
    }

    BackHandler(enabled = !isNowPlayingExpanded && selectedPlaylist != null) {
        viewModel.setSelectedPlaylist(null)
    }

    BackHandler(enabled = !isNowPlayingExpanded && selectedPlaylist == null && currentTab != AppNavTab.HOME) {
        viewModel.selectTab(AppNavTab.HOME)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                Column(modifier = Modifier.navigationBarsPadding()) {
                    // Docked MiniPlayer (only shows when there is a current track and full player is not expanded)
                    if (currentTrack != null && !isNowPlayingExpanded) {
                        MiniPlayer(
                            track = currentTrack,
                            isPlaying = isPlaying,
                            isBuffering = isBuffering,
                            currentPositionMs = currentPositionMs,
                            durationMs = durationMs,
                            onPlayPauseClick = { viewModel.togglePlayPause() },
                            onSkipNextClick = { viewModel.skipNext() },
                            onExpandClick = { viewModel.setNowPlayingExpanded(true) }
                        )
                    }

                    // M3 Bottom Navigation Bar
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 8.dp,
                        modifier = Modifier.testTag("bottom_nav_bar")
                    ) {
                        NavigationBarItem(
                            selected = currentTab == AppNavTab.HOME,
                            onClick = { viewModel.selectTab(AppNavTab.HOME) },
                            icon = {
                                Icon(
                                    imageVector = if (currentTab == AppNavTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                                    contentDescription = "Home"
                                )
                            },
                            label = { Text("Home", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = ElectricViolet,
                                selectedTextColor = ElectricViolet,
                                indicatorColor = ElectricViolet.copy(alpha = 0.15f)
                            ),
                            modifier = Modifier.testTag("nav_home")
                        )

                        NavigationBarItem(
                            selected = currentTab == AppNavTab.SEARCH,
                            onClick = { viewModel.selectTab(AppNavTab.SEARCH) },
                            icon = {
                                Icon(
                                    imageVector = if (currentTab == AppNavTab.SEARCH) Icons.Filled.Search else Icons.Outlined.Search,
                                    contentDescription = "Search"
                                )
                            },
                            label = { Text("Search", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = ElectricViolet,
                                selectedTextColor = ElectricViolet,
                                indicatorColor = ElectricViolet.copy(alpha = 0.15f)
                            ),
                            modifier = Modifier.testTag("nav_search")
                        )

                        NavigationBarItem(
                            selected = currentTab == AppNavTab.LIBRARY,
                            onClick = { viewModel.selectTab(AppNavTab.LIBRARY) },
                            icon = {
                                Icon(
                                    imageVector = if (currentTab == AppNavTab.LIBRARY) Icons.Filled.LibraryMusic else Icons.Outlined.LibraryMusic,
                                    contentDescription = "Library"
                                )
                            },
                            label = { Text("Library", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = ElectricViolet,
                                selectedTextColor = ElectricViolet,
                                indicatorColor = ElectricViolet.copy(alpha = 0.15f)
                            ),
                            modifier = Modifier.testTag("nav_library")
                        )

                        NavigationBarItem(
                            selected = currentTab == AppNavTab.EQUALIZER,
                            onClick = { viewModel.selectTab(AppNavTab.EQUALIZER) },
                            icon = {
                                Icon(
                                    imageVector = if (currentTab == AppNavTab.EQUALIZER) Icons.Filled.GraphicEq else Icons.Outlined.GraphicEq,
                                    contentDescription = "Equalizer"
                                )
                            },
                            label = { Text("Equalizer", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = ElectricViolet,
                                selectedTextColor = ElectricViolet,
                                indicatorColor = ElectricViolet.copy(alpha = 0.15f)
                            ),
                            modifier = Modifier.testTag("nav_equalizer")
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .statusBarsPadding()
            ) {
                when (currentTab) {
                    AppNavTab.HOME -> {
                        HomeScreen(
                            tracks = filteredTracks,
                            playlists = playlists,
                            currentTrack = currentTrack,
                            isPlaying = isPlaying,
                            isOfflineOnlyMode = isOfflineOnlyMode,
                            streamingQuality = streamingQuality,
                            selectedGenre = selectedGenreFilter,
                            downloadProgress = downloadProgress,
                            onToggleOfflineOnly = { viewModel.toggleOfflineOnlyMode() },
                            onGenreSelect = { genre -> viewModel.setGenreFilter(genre) },
                            onTrackClick = { track -> viewModel.playTrack(track) },
                            onFavoriteToggle = { track -> viewModel.toggleFavorite(track) },
                            onDownloadClick = { track -> viewModel.downloadTrack(track) },
                            onAddToPlaylistClick = { track -> viewModel.openAddToPlaylist(track) },
                            onShareClick = { track -> viewModel.openShareDialog(track) },
                            onOpenProfileSync = { viewModel.openProfileSyncDialog() },
                            onOpenAudioQuality = { viewModel.openAudioQualityDialog() },
                            onOpenEqualizer = { viewModel.selectTab(AppNavTab.EQUALIZER) }
                        )
                    }

                    AppNavTab.SEARCH -> {
                        SearchScreen(
                            searchQuery = searchQuery,
                            filteredTracks = filteredTracks,
                            currentTrack = currentTrack,
                            isPlaying = isPlaying,
                            downloadProgress = downloadProgress,
                            onSearchChange = { query -> viewModel.setSearchQuery(query) },
                            onTrackClick = { track -> viewModel.playTrack(track) },
                            onFavoriteToggle = { track -> viewModel.toggleFavorite(track) },
                            onDownloadClick = { track -> viewModel.downloadTrack(track) },
                            onAddToPlaylistClick = { track -> viewModel.openAddToPlaylist(track) },
                            onShareClick = { track -> viewModel.openShareDialog(track) }
                        )
                    }

                    AppNavTab.LIBRARY -> {
                        LibraryScreen(
                            playlists = playlists,
                            allTracks = allTracks,
                            selectedPlaylist = selectedPlaylist,
                            currentTrack = currentTrack,
                            isPlaying = isPlaying,
                            downloadProgress = downloadProgress,
                            onSelectPlaylist = { pl -> viewModel.setSelectedPlaylist(pl) },
                            onCreatePlaylistClick = { viewModel.openCreatePlaylistDialog() },
                            onDeletePlaylist = { id -> viewModel.deletePlaylist(id) },
                            onTrackClick = { track -> viewModel.playTrack(track) },
                            onFavoriteToggle = { track -> viewModel.toggleFavorite(track) },
                            onDownloadClick = { track -> viewModel.downloadTrack(track) },
                            onAddToPlaylistClick = { track -> viewModel.openAddToPlaylist(track) },
                            onRemoveFromPlaylist = { pl, trackId -> viewModel.removeTrackFromPlaylist(pl, trackId) },
                            onShareClick = { track -> viewModel.openShareDialog(track) },
                            onScanDeviceAudio = { requestScanWithPermission() },
                            onImportAudioFiles = { uris -> viewModel.importDeviceAudioFiles(uris) },
                            isScanningDevice = isScanningDevice,
                            scanMessage = scanMessage,
                            onClearScanMessage = { viewModel.clearScanMessage() }
                        )
                    }

                    AppNavTab.EQUALIZER -> {
                        EqualizerScreen(
                            equalizerState = equalizerState,
                            isPlaying = isPlaying,
                            onToggleEnabled = { viewModel.toggleEqualizerEnabled() },
                            onSelectPreset = { preset -> viewModel.setEqualizerPreset(preset) },
                            onBandGainChange = { band, gain -> viewModel.setEqualizerBandGain(band, gain) },
                            onBassBoostChange = { level -> viewModel.setBassBoost(level) },
                            onVirtualizerChange = { level -> viewModel.setVirtualizer(level) }
                        )
                    }
                }
            }
        }

        // Fullscreen Animated Now Playing Sheet
        AnimatedVisibility(
            visible = isNowPlayingExpanded && currentTrack != null,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            currentTrack?.let { track ->
                NowPlayingSheet(
                    track = track,
                    isPlaying = isPlaying,
                    isBuffering = isBuffering,
                    currentPositionMs = currentPositionMs,
                    durationMs = durationMs,
                    isShuffleEnabled = isShuffleEnabled,
                    repeatMode = repeatMode,
                    downloadProgress = downloadProgress[track.id],
                    showLyrics = showLyrics,
                    onCloseClick = { viewModel.setNowPlayingExpanded(false) },
                    onPlayPauseClick = { viewModel.togglePlayPause() },
                    onSeek = { pos -> viewModel.seekTo(pos) },
                    onSkipNextClick = { viewModel.skipNext() },
                    onSkipPreviousClick = { viewModel.skipPrevious() },
                    onToggleShuffle = { viewModel.toggleShuffle() },
                    onToggleRepeat = { viewModel.toggleRepeat() },
                    onToggleFavorite = { viewModel.toggleFavorite(track) },
                    onDownloadClick = { viewModel.downloadTrack(track) },
                    onToggleLyrics = { viewModel.toggleShowLyrics() },
                    onOpenEqualizer = {
                        viewModel.setNowPlayingExpanded(false)
                        viewModel.selectTab(AppNavTab.EQUALIZER)
                    },
                    onOpenShare = { viewModel.openShareDialog(track) }
                )
            }
        }

        // Dialogs
        if (showCreatePlaylistDialog) {
            CreatePlaylistDialog(
                onDismiss = { viewModel.closeCreatePlaylistDialog() },
                onConfirm = { title, desc, emoji ->
                    viewModel.createPlaylist(title, desc, emoji)
                }
            )
        }

        showAddToPlaylistTrack?.let { track ->
            AddToPlaylistDialog(
                track = track,
                playlists = playlists,
                onDismiss = { viewModel.closeAddToPlaylist() },
                onPlaylistSelected = { pl ->
                    viewModel.addTrackToPlaylist(pl, track.id)
                }
            )
        }

        showShareDialogTrack?.let { track ->
            ShareTrackDialog(
                track = track,
                onDismiss = { viewModel.closeShareDialog() },
                onShareExternally = { quote ->
                    viewModel.shareTrackExternally(context, track, quote)
                }
            )
        }

        if (showProfileSyncDialog) {
            ProfileSyncDialog(
                profile = userProfile,
                onDismiss = { viewModel.closeProfileSyncDialog() },
                onExportSyncData = { viewModel.generateExportSyncJson() },
                onImportSyncData = { json, cb -> viewModel.importSyncJson(json, cb) }
            )
        }

        if (showAudioQualityDialog) {
            AudioQualityDialog(
                currentQuality = streamingQuality,
                onSelectQuality = { q -> viewModel.setStreamingQuality(q) },
                onDismiss = { viewModel.closeAudioQualityDialog() }
            )
        }
    }
}
