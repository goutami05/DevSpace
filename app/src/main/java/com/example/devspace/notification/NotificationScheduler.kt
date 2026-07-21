package com.example.devspace.notification

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object NotificationScheduler {

    private const val WORK_NAME = "devspace_notification_work"

    fun scheduleNotifications(context: Context) {

        val workRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(
                15,
                TimeUnit.MINUTES
            ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }
}