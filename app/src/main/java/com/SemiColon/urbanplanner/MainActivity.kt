package com.SemiColon.urbanplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.SemiColon.urbanplanner.navigation.AppNavigation


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.SemiColon.urbanplanner.utils.PreferencesManager
import com.SemiColon.urbanplanner.ui.theme.UrbanPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SupabaseClient.initialize(this)
        enableEdgeToEdge()
        setContent {
            val preferencesManager = remember { PreferencesManager(this) }
            val currentTheme by preferencesManager.appTheme.collectAsState()
            val navController = rememberNavController()

            UrbanPlannerTheme(appTheme = currentTheme, darkTheme = true) {
                AppNavigation(navController = navController, preferencesManager = preferencesManager)
            }
        }
    }
}