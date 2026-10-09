package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.OfflinePin
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.model.Track
import com.example.ui.theme.CyanAura
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.SunsetCoral
import com.example.viewmodel.StreamingQuality

@Composable
fun HomeScreen(
    tracks: List<Track>,
    playlists: List<PlaylistEntity>,
    currentTrack: Track?,
    isPlaying: Boolean,
    isOfflineOnlyMode: Boolean,
    streamingQuality: StreamingQuality,
    selectedGenre: String?,
    downloadProgress: Map<String, Float>,
    onToggleOfflineOnly: () -> Unit,
    onGenreSelect: (String?) -> Unit,
    onTrackClick: (Track) -> Unit,
    onFavoriteToggle: (Track) -> Unit,
    onDownloadClick: (Track) -> Unit,
    onAddToPlaylistClick: (Track) -> Unit,
    onShareClick: (Track) -> Unit,
    onOpenProfileSync: () -> Unit,
    onOpenAudioQuality: () -> Unit,
    onOpenEqualizer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val genres = listOf("All", "Nusrat Fateh Ali Khan", "Arijit Singh", "Talha Anjum", "Asim Azhar", "Bilal Saeed", "Pakistani Pop", "Sufi", "Qawwali", "Punjabi", "Romantic", "Hip-Hop", "Acoustic", "Pop", "Rock", "EDM", "Lo-Fi", "Synthwave", "Ambient", "Classical", "Jazz", "Indie")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("home_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top App Header
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(ElectricViolet, SunsetCoral)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Headphones,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "Music Libreriya",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                letterSpacing = (-0.5).sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Audiophile Studio Player",
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Offline Mode Filter Pill Toggle
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (isOfflineOnlyMode) CyanAura
                                else MaterialTheme.colorScheme.surfaceVariant
                            )
                            .clickable(onClick = onToggleOfflineOnly)
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("offline_filter_toggle")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.OfflinePin,
                                contentDescription = null,
                                tint = if (isOfflineOnlyMode) Color.Black else CyanAura,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isOfflineOnlyMode) "Offline" else "Online",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                ),
                                color = if (isOfflineOnlyMode) Color.Black else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Profile Sync Avatar
                    IconButton(
                        onClick = onOpenProfileSync,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("profile_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Audiophile Profile & Sync",
                            tint = ElectricViolet,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }

        // Hero Audiophile Showcase Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        tracks.firstOrNull()?.let { onTrackClick(it) }
                    },
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF2E1065),
                                    Color(0xFF4C1D95),
                                    Color(0xFF831843)
                                )
                            )
                        )
                        .padding(18.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(CyanAura.copy(alpha = 0.2f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Bolt,
                                        contentDescription = null,
                                        tint = CyanAura,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = streamingQuality.bitrate,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp
                                        ),
                                        color = CyanAura
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.15f))
                                    .clickable(onClick = onOpenAudioQuality)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = streamingQuality.label,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 10.sp
                                    ),
                                    color = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Ultra Hi-Res Audio Experience",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            ),
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Uncompressed FLAC streaming & 100% genuine offline download playback with DSP sound tuning.",
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                            color = Color.White.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color.White)
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Play Master Release",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    ),
                                    color = Color.Black
                                )
                            }

                            IconButton(
                                onClick = onOpenEqualizer,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.15f))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Tune,
                                    contentDescription = "Equalizer",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Quick Jump Playlist Cards (2x2 grid)
        item {
            Text(
                text = "QUICK JUMP",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickPlayCard(
                        title = "Pakistani & Coke Studio",
                        emoji = "🇵🇰",
                        gradientColors = listOf(Color(0xFF047857), Color(0xFF065F46)),
                        modifier = Modifier.weight(1f),
                        onClick = {
                            tracks.find { it.title.contains("Pasoori", ignoreCase = true) || it.genre.contains("Pakistani", ignoreCase = true) }?.let { onTrackClick(it) }
                                ?: tracks.firstOrNull()?.let { onTrackClick(it) }
                        }
                    )
                    QuickPlayCard(
                        title = "Liked Songs",
                        emoji = "❤️",
                        gradientColors = listOf(SunsetCoral, Color(0xFFBE185D)),
                        modifier = Modifier.weight(1f),
                        onClick = {
                            tracks.filter { it.isFavorite }.firstOrNull()?.let { onTrackClick(it) }
                                ?: tracks.firstOrNull()?.let { onTrackClick(it) }
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickPlayCard(
                        title = "Late Night Focus",
                        emoji = "🌙",
                        gradientColors = listOf(CyanAura, Color(0xFF0E7490)),
                        modifier = Modifier.weight(1f),
                        onClick = {
                            tracks.find { it.genre == "Lo-Fi" || it.genre == "Ambient" }?.let { onTrackClick(it) }
                                ?: tracks.firstOrNull()?.let { onTrackClick(it) }
                        }
                    )
                    QuickPlayCard(
                        title = "Acoustic Warmth",
                        emoji = "🎸",
                        gradientColors = listOf(Color(0xFFF59E0B), Color(0xFFD97706)),
                        modifier = Modifier.weight(1f),
                        onClick = {
                            tracks.find { it.genre == "Acoustic" }?.let { onTrackClick(it) }
                                ?: tracks.firstOrNull()?.let { onTrackClick(it) }
                        }
                    )
                }
            }
        }

        // Featured Artist Spotlights (NFAK, Arijit Singh, Talha Anjum, Asim Azhar, Bilal Saeed)
        item {
            Text(
                text = "ARTIST SPOTLIGHT",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val spotlights = listOf(
                    Triple("Nusrat Fateh Ali Khan", "🕊️", listOf(Color(0xFF78350F), Color(0xFF1E1B4B))),
                    Triple("Arijit Singh", "💔", listOf(Color(0xFFDC2626), Color(0xFF7C2D12))),
                    Triple("Talha Anjum", "🎤", listOf(Color(0xFF18181B), Color(0xFF450A0A))),
                    Triple("Asim Azhar", "✨", listOf(Color(0xFF7C3AED), Color(0xFFDB2777))),
                    Triple("Bilal Saeed", "🥁", listOf(Color(0xFFD97706), Color(0xFF78350F)))
                )

                spotlights.forEach { (artistName, iconEmoji, grad) ->
                    val isArtistSelected = selectedGenre == artistName
                    Card(
                        modifier = Modifier
                            .width(135.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable {
                                onGenreSelect(if (isArtistSelected) null else artistName)
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isArtistSelected) ElectricViolet.copy(alpha = 0.25f)
                            else MaterialTheme.colorScheme.surfaceVariant
                        ),
                        border = if (isArtistSelected) androidx.compose.foundation.BorderStroke(1.5.dp, ElectricViolet) else null
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(Brush.linearGradient(grad)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(iconEmoji, fontSize = 20.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = artistName,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }

        // Genre Filter Chips
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                genres.forEach { genre ->
                    val isSelected = (genre == "All" && selectedGenre == null) || (selectedGenre == genre)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(
                                if (isSelected) ElectricViolet
                                else MaterialTheme.colorScheme.surfaceVariant
                            )
                            .clickable {
                                onGenreSelect(if (genre == "All") null else genre)
                            }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = genre,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 12.sp
                            ),
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // Featured Songs Section Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isOfflineOnlyMode) "DOWNLOADED FOR OFFLINE (${tracks.size})" else "FEATURED TRACKS (${tracks.size})",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (isOfflineOnlyMode && tracks.isEmpty()) {
                    Text(
                        text = "No offline downloads yet",
                        style = MaterialTheme.typography.labelSmall,
                        color = SunsetCoral
                    )
                }
            }
        }

        // Track rows
        items(tracks, key = { it.id }) { track ->
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
            Spacer(modifier = Modifier.height(90.dp)) // Mini-player bottom inset
        }
    }
}

@Composable
fun QuickPlayCard(
    title: String,
    emoji: String,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .background(Brush.linearGradient(gradientColors)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 22.sp)
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            )
        }
    }
}
