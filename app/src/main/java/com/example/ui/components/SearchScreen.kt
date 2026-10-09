package com.example.ui.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Track
import com.example.ui.theme.CyanAura
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.SunsetCoral

data class GenreCategory(
    val name: String,
    val subtitle: String,
    val gradient: List<Color>,
    val emoji: String
)

@Composable
fun SearchScreen(
    searchQuery: String,
    filteredTracks: List<Track>,
    currentTrack: Track?,
    isPlaying: Boolean,
    downloadProgress: Map<String, Float>,
    onSearchChange: (String) -> Unit,
    onTrackClick: (Track) -> Unit,
    onFavoriteToggle: (Track) -> Unit,
    onDownloadClick: (Track) -> Unit,
    onAddToPlaylistClick: (Track) -> Unit,
    onShareClick: (Track) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        GenreCategory("Nusrat Fateh Ali Khan", "The Qawwali & Sufi Masterpieces", listOf(Color(0xFF78350F), Color(0xFF1E1B4B)), "👑"),
        GenreCategory("Arijit Singh", "The Voice of Modern Bollywood", listOf(Color(0xFFDC2626), Color(0xFF7C2D12)), "🎙️"),
        GenreCategory("Talha Anjum", "Urdu Rap & Hip-Hop Icon", listOf(Color(0xFF18181B), Color(0xFF450A0A)), "🔥"),
        GenreCategory("Asim Azhar", "Pakistani Pop & R&B Hits", listOf(Color(0xFF7C3AED), Color(0xFFDB2777)), "✨"),
        GenreCategory("Bilal Saeed", "Pioneer of Punjabi Pop & Bhangra", listOf(Color(0xFFD97706), Color(0xFF78350F)), "🥁"),
        GenreCategory("Pakistani", "Coke Studio, Sufi & Pop Classics", listOf(Color(0xFF047857), Color(0xFF065F46)), "🇵🇰"),
        GenreCategory("Sufi", "Spiritual Ecstasy & Mystical Ghazals", listOf(Color(0xFF7C2D12), Color(0xFFF59E0B)), "🕊️"),
        GenreCategory("Qawwali", "Devotional Harmonium & Tabla Energy", listOf(Color(0xFF1E3A8A), Color(0xFF047857)), "🪕"),
        GenreCategory("Punjabi", "High-Energy Bhangra & Dhol Grooves", listOf(Color(0xFFEAB308), Color(0xFF16A34A)), "⚡"),
        GenreCategory("Pop", "Sunlit Hits & Euphoric Hooks", listOf(Color(0xFFFF7043), Color(0xFFFFCA28)), "🌟"),
        GenreCategory("Hip-Hop", "808 Basslines & Midnight Flows", listOf(Color(0xFFEF4444), Color(0xFF18181B)), "🎤"),
        GenreCategory("R&B", "Velvet Harmonies & Soul Grooves", listOf(Color(0xFF9333EA), Color(0xFFBE185D)), "🍷"),
        GenreCategory("Rock", "Thunderous Riffs & Power Anthems", listOf(Color(0xFFB91C1C), Color(0xFF78350F)), "🎸"),
        GenreCategory("EDM", "Festival Drops & Laser Beams", listOf(Color(0xFF0284C7), Color(0xFFF43F5E)), "🎉"),
        GenreCategory("Lo-Fi", "Mellow Study & Coffee Rain", listOf(Color(0xFF3B82F6), Color(0xFF10B981)), "☕"),
        GenreCategory("Acoustic", "Warm Unplugged Guitars", listOf(Color(0xFFF59E0B), Color(0xFFEF4444)), "🎶")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("search_screen")
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Search Bar Input
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("search_input"),
            placeholder = {
                Text(
                    "Search songs, artists, genres, albums...",
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = ElectricViolet
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedBorderColor = ElectricViolet,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchQuery.isBlank()) {
            // Explore Genres Grid
            Text(
                text = "EXPLORE GENRES & AUDIO THEMES",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(categories) { cat ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { onSearchChange(cat.name) },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Brush.horizontalGradient(cat.gradient))
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = cat.name,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    ),
                                    color = Color.White
                                )
                                Text(
                                    text = cat.subtitle,
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                    color = Color.White.copy(alpha = 0.85f)
                                )
                            }

                            Text(
                                text = cat.emoji,
                                fontSize = 32.sp
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        } else {
            // Search Results List
            Text(
                text = "SEARCH RESULTS (${filteredTracks.size})",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (filteredTracks.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "🔍",
                            fontSize = 44.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No tracks found matching \"$searchQuery\"",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Try searching for Synthwave, Lo-Fi, Acoustic, or Jazz",
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
                    items(filteredTracks, key = { it.id }) { track ->
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
