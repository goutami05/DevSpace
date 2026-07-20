package com.example.devspace.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "theme_preferences")

class ThemePreferences(private val context: Context) {

    companion object {
        private val DARK_THEME = booleanPreferencesKey("dark_theme")
    }

    val isDarkTheme: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[DARK_THEME] ?: false
        }

    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME] = isDark
        }
    }
}