package com.SemiColon.urbanplanner.utils

import android.content.Context
import com.SemiColon.urbanplanner.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _appTheme = MutableStateFlow(readThemeFromPrefs())
    val appTheme: StateFlow<AppTheme> = _appTheme.asStateFlow()

    fun setAppTheme(theme: AppTheme) {
        prefs.edit().putString(KEY_APP_THEME, theme.name).apply()
        _appTheme.value = theme
    }

    private fun readThemeFromPrefs(): AppTheme {
        val stored = prefs.getString(KEY_APP_THEME, AppTheme.DEFAULT.name)
        return runCatching { AppTheme.valueOf(stored ?: AppTheme.DEFAULT.name) }
            .getOrDefault(AppTheme.DEFAULT)
    }

    private companion object {
        const val PREFS_NAME = "urban_planner_prefs"
        const val KEY_APP_THEME = "app_theme"
    }
}

