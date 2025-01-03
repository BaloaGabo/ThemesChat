package com.example.themeschat.ui.apptheme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.themeschat.enums.Themes
import com.example.themeschat.ui.apptheme.theme.natural.darkNaturalScheme
import com.example.themeschat.ui.apptheme.theme.natural.lightNaturalScheme
import com.example.themeschat.ui.apptheme.theme.original.darkOriginalScheme
import com.example.themeschat.ui.apptheme.theme.original.lightOriginalScheme
import com.example.themeschat.ui.apptheme.theme.sunset.darkSunsetScheme
import com.example.themeschat.ui.apptheme.theme.sunset.lightSunsetScheme

@Composable
fun AppTheme(
    //elegir un tema - Original - Natural - Sunset
    themes: Themes,
    //solo aplica para la version 12+ de android
    //lo que es igual a API 31+
    isDynamicColors: Boolean = false,
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    //TODO: set ColorSchemes

    val colors: ColorScheme

    if (isDynamicColors && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){

        val contextLocal = LocalContext.current

        colors = if (isDarkMode) dynamicDarkColorScheme(contextLocal) else dynamicLightColorScheme(contextLocal)

    }else{

        colors = when(themes){
            Themes.ORIGINAL -> if (isDarkMode) darkOriginalScheme else lightOriginalScheme
            Themes.NATURAL -> if (isDarkMode) darkNaturalScheme else lightNaturalScheme
            Themes.SUNSET -> if (isDarkMode) darkSunsetScheme else lightSunsetScheme
        }
        }
    // TODO: set Typograpy

    MaterialTheme(
        colorScheme = colors,
        content = content
    )


}