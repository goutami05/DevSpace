package com.example.devspace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.devspace.datastore.ThemePreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val preferences: ThemePreferences
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> =
        preferences.isDarkTheme.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun toggleTheme(enabled: Boolean) {
        viewModelScope.launch {
            preferences.saveTheme(enabled)
        }
    }
}

class ThemeViewModelFactory(
    private val preferences: ThemePreferences
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            return ThemeViewModel(preferences) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}