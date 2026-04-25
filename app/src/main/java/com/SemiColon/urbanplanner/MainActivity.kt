package com.SemiColon.urbanplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.SemiColon.urbanplanner.navigation.AppNavigator


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.SemiColon.urbanplanner.ui.theme.ThemeManager
import com.SemiColon.urbanplanner.ui.theme.UrbanPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SupabaseClient.initialize(this)
        enableEdgeToEdge()
        setContent {
            val themeManager = ThemeManager.getInstance(this)
            val currentThemeMode by themeManager.themeMode.collectAsState()

            UrbanPlannerTheme(themeMode = currentThemeMode) {
                AppNavigator()
            }
        }
    }
}