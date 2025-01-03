package com.example.themeschat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.themeschat.enums.Themes
import com.example.themeschat.ui.apptheme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var themeSelected by rememberSaveable {
                mutableStateOf<Themes>(Themes.ORIGINAL)
            }

            AppTheme(
                themes = themeSelected
            ) {
                ChatScream(changeThemes = {
                    themeSelected = it
                })

            }
        }
    }
}

