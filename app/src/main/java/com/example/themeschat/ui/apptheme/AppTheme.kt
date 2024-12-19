package com.example.themeschat.ui.apptheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    //TODO: set ColorSchemes

    // TODO: set Typograpy

    MaterialTheme(
        content = content
    )


}