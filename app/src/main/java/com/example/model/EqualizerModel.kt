package com.example.model

data class EqualizerBand(
    val index: Int,
    val name: String,
    val freqHz: Int,
    val gainDb: Float // -12dB to +12dB
)

data class EqualizerPreset(
    val name: String,
    val gains: List<Float>,
    val bassBoost: Float,
    val virtualizer: Float
)

data class EqualizerState(
    val isEnabled: Boolean = true,
    val bands: List<EqualizerBand> = defaultBands(),
    val bassBoostLevel: Float = 40f, // 0 - 100%
    val virtualizerLevel: Float = 30f, // 0 - 100%
    val currentPreset: String = "Audiophile HiFi",
    val hiResMode: Boolean = true
) {
    companion object {
        fun defaultBands(): List<EqualizerBand> = listOf(
            EqualizerBand(0, "Sub-Bass", 60, 3f),
            EqualizerBand(1, "Bass", 230, 2f),
            EqualizerBand(2, "Midrange", 910, 0f),
            EqualizerBand(3, "Upper Mid", 3600, 2f),
            EqualizerBand(4, "Treble", 14000, 4f)
        )

        val PRESETS = listOf(
            EqualizerPreset("Flat", listOf(0f, 0f, 0f, 0f, 0f), 0f, 0f),
            EqualizerPreset("Audiophile HiFi", listOf(3f, 2f, 0f, 2f, 4f), 35f, 30f),
            EqualizerPreset("Bass Booster", listOf(7f, 6f, 1f, -1f, 1f), 80f, 20f),
            EqualizerPreset("Electronic", listOf(5f, 3f, -1f, 3f, 6f), 55f, 45f),
            EqualizerPreset("Rock", listOf(4f, 2f, -1f, 3f, 5f), 40f, 30f),
            EqualizerPreset("Vocal / Acoustic", listOf(-1f, 1f, 4f, 4f, 2f), 15f, 25f),
            EqualizerPreset("Jazz", listOf(2f, 3f, 1f, 2f, 3f), 25f, 35f),
            EqualizerPreset("Deep Lounge", listOf(6f, 4f, 0f, 1f, 3f), 65f, 50f)
        )
    }
}
