package com.example.devspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.devspace.datastore.ThemePreferences
import com.example.devspace.navigation.AppNavGraph
import com.example.devspace.navigation.BottomBar
import com.example.devspace.navigation.Screen
import com.example.devspace.ui.theme.DevSpaceTheme
import com.example.devspace.viewmodel.ThemeViewModel
import com.example.devspace.viewmodel.ThemeViewModelFactory
import androidx.compose.material3.Scaffold
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import com.example.devspace.notification.NotificationHelper
import com.example.devspace.notification.NotificationScheduler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val context = LocalContext.current

            val themePreferences = ThemePreferences(context)

            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModelFactory(themePreferences)
            )

            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            var showNotificationDialog by remember {
                mutableStateOf(false)
            }

            val notificationPermissionLauncher =
                rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission()
                ) { isGranted ->

                    if (isGranted) {
                        // Permission granted
                    } else {
                        // Permission denied
                    }

                }

            LaunchedEffect(Unit) {

                NotificationHelper.createNotificationChannel(context)
                NotificationScheduler.scheduleNotifications(context)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    if (
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {

                        showNotificationDialog = true

                    }

                }

            }

            if (showNotificationDialog) {

                AlertDialog(

                    onDismissRequest = {
                        showNotificationDialog = false
                    },

                    title = {
                        Text("Stay Updated 🔔")
                    },

                    text = {
                        Text(
                            "Enable notifications to receive the latest tech news, trending repositories, and useful developer updates from DevSpace."
                        )
                    },

                    confirmButton = {

                        TextButton(

                            onClick = {

                                showNotificationDialog = false

                                notificationPermissionLauncher.launch(
                                    Manifest.permission.POST_NOTIFICATIONS
                                )

                            }

                        ) {

                            Text("Allow")

                        }

                    },

                    dismissButton = {

                        TextButton(

                            onClick = {

                                showNotificationDialog = false

                            }

                        ) {

                            Text("Not Now")

                        }

                    }

                )

            }

            DevSpaceTheme(
                darkTheme = isDarkTheme
            ) {

                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val currentRoute = navBackStackEntry?.destination?.route

                val showBottomBar = currentRoute in listOf(
                    Screen.Home.route,
                    Screen.Repo.route,
                    Screen.Bookmark.route
                )

                Scaffold(

                    bottomBar = {

                        if (showBottomBar) {

                            BottomBar(navController)

                        }

                    }

                ) { innerPadding ->

                    AppNavGraph(

                        navController = navController,

                        modifier = Modifier.padding(innerPadding)

                    )

                }

            }

        }

    }

}