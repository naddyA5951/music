package com.example.model

data class Playlist(
    val id: String,
    val title: String,
    val description: String,
    val trackIds: List<String> = emptyList(),
    val emoji: String = "🎵",
    val isCustom: Boolean = true,
    val gradientStart: Long = 0xFF8B5CF6,
    val gradientEnd: Long = 0xFF3B82F6,
    val createdAt: Long = System.currentTimeMillis()
)
