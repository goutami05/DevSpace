package com.example.devspace.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.devspace.notification.NotificationMessages
import kotlinx.coroutines.flow.first
import kotlin.random.Random

private val Context.notificationDataStore by preferencesDataStore("notification_preferences")

class NotificationPreferences(
    private val context: Context
) {

    companion object {
        private val LAST_INDEX =
            intPreferencesKey("last_notification_index")
    }

    suspend fun getRandomNotificationIndex(): Int {

        val preferences = context.notificationDataStore.data.first()

        val lastIndex = preferences[LAST_INDEX] ?: -1

        var newIndex: Int

        do {
            newIndex = Random.nextInt(NotificationMessages.messages.size)
        } while (
            NotificationMessages.messages.size > 1 &&
            newIndex == lastIndex
        )

        context.notificationDataStore.edit {
            it[LAST_INDEX] = newIndex
        }

        return newIndex
    }
}