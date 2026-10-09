package com.example.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ElectricVioletLight,
    onPrimary = ObsidianBg,
    primaryContainer = ElectricVioletDark,
    onPrimaryContainer = TextPrimaryDark,
    secondary = SunsetCoralLight,
    onSecondary = ObsidianBg,
    secondaryContainer = SunsetCoral,
    onSecondaryContainer = TextPrimaryDark,
    tertiary = CyanAuraLight,
    onTertiary = ObsidianBg,
    background = ObsidianBg,
    onBackground = TextPrimaryDark,
    surface = ObsidianSurface,
    onSurface = TextPrimaryDark,
    surfaceVariant = ObsidianSurfaceElevated,
    onSurfaceVariant = TextSecondaryDark,
    outline = ObsidianSurfaceBorder
)

private val LightColorScheme = lightColorScheme(
    primary = ElectricViolet,
    onPrimary = PorcelainSurface,
    primaryContainer = ElectricVioletLight,
    onPrimaryContainer = TextPrimaryLight,
    secondary = SunsetCoral,
    onSecondary = PorcelainSurface,
    secondaryContainer = SunsetCoralLight,
    onSecondaryContainer = TextPrimaryLight,
    tertiary = CyanAura,
    onTertiary = PorcelainSurface,
    background = PorcelainBg,
    onBackground = TextPrimaryLight,
    surface = PorcelainSurface,
    onSurface = TextPrimaryLight,
    surfaceVariant = PorcelainSurfaceElevated,
    onSurfaceVariant = TextSecondaryLight,
    outline = ObsidianSurfaceBorder.copy(alpha = 0.2f)
)

@Composable
fun MusicLibreriyaTheme(
    darkTheme: Boolean = true, // Default to audiophile dark mode
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                window.navigationBarColor = colorScheme.background.toArgb()
                val controller = WindowCompat.getInsetsController(window, view)
                controller.isAppearanceLightStatusBars = !darkTheme
                controller.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
