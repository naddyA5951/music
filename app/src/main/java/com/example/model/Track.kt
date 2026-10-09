package com.example.model

import java.util.Locale

data class LyricLine(
    val timestampMs: Long,
    val text: String
)

data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val durationMs: Long,
    val streamUrl: String = "",
    val localFilePath: String? = null,
    val isDownloaded: Boolean = false,
    val fileSizeBytes: Long = 0L,
    val genre: String = "Electronic",
    val lyrics: List<LyricLine> = emptyList(),
    val coverGradientStart: Long = 0xFF7C3AED, // Hex ARGB
    val coverGradientEnd: Long = 0xFFEC4899,
    val iconCategory: String = "headphones",
    val bitrateKbps: Int = 320,
    val audioFormat: String = "FLAC 24-bit / 48kHz",
    val isFavorite: Boolean = false,
    val isLocalDeviceTrack: Boolean = false,
    val playsCount: Int = 0
) {
    val durationFormatted: String
        get() = formatDuration(durationMs)

    val sizeFormatted: String
        get() = if (fileSizeBytes > 0) {
            String.format(Locale.US, "%.1f MB", fileSizeBytes / (1024f * 1024f))
        } else {
            String.format(Locale.US, "%.1f MB", (durationMs / 1000f) * (bitrateKbps / 8f) / 1024f)
        }
}

fun formatDuration(ms: Long): String {
    val totalSeconds = (ms / 1000).coerceAtLeast(0)
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format(Locale.US, "%d:%02d", minutes, seconds)
}
