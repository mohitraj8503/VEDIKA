package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val VedikaColorScheme = lightColorScheme(
    primary = VedikaOrange,
    onPrimary = VedikaSurface,
    primaryContainer = VedikaSaffronLight,
    onPrimaryContainer = VedikaOrangeDark,
    secondary = VedikaAmber,
    onSecondary = VedikaSurface,
    secondaryContainer = VedikaCreamDark,
    onSecondaryContainer = VedikaGold,
    tertiary = VedikaMaroon,
    onTertiary = VedikaSurface,
    background = VedikaCream,
    onBackground = VedikaTextDark,
    surface = VedikaSurface,
    onSurface = VedikaTextDark,
    surfaceVariant = VedikaCreamDark,
    onSurfaceVariant = VedikaTextMedium,
    outline = VedikaCardBorder,
    outlineVariant = VedikaDivider
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VedikaColorScheme,
        typography = Typography,
        content = content
    )
}
