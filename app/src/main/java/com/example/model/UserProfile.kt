package com.example.model

data class UserProfile(
    val username: String = "Audiophile Alex",
    val email: String = "naveedalicodes1@gmail.com",
    val tier: String = "Music Libreriya Ultra Hi-Res",
    val totalMinutesListened: Long = 1840L,
    val tracksDownloadedCount: Int = 12,
    val playlistsCount: Int = 4,
    val syncDeviceId: String = "LIB-ANDR-9842",
    val lastSyncTimestamp: Long = System.currentTimeMillis()
)
