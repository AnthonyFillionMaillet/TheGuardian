package com.afillionmaillet.theguardian.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    onPrimary = White,
    secondary = Pink,
    onSecondary = White,
    background = Black,
)

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    onPrimary = White,
    secondary = Pink,
    onSecondary = White,
    background = LightGrey,
)

@Composable
fun TheGuardianAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = PrimaryDark
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = PrimaryDark
        )
    }

    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}