package com.example.devspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.devspace.navigation.AppNavGraph
import com.example.devspace.navigation.Screen
import com.example.devspace.ui.theme.DevSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevSpaceTheme {
                val navController = rememberNavController()

                // Tracks the current active screen route in real-time
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // 🌟 Core Fix: Define which screens should display the navigation tabs
                val showBottomBar = currentRoute in listOf(
                    Screen.Home.route,
                    Screen.Repo.route,
                    Screen.Bookmark.route
                )

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                // 🏠 Home Tab Layout
                                NavigationBarItem(
                                    selected = currentRoute == Screen.Home.route,
                                    onClick = {
                                        if (currentRoute != Screen.Home.route) {
                                            navController.navigate(Screen.Home.route) {
                                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                    label = { Text("Home") }
                                )

                                // 💻 Repositories Tab Layout
                                NavigationBarItem(
                                    selected = currentRoute == Screen.Repo.route,
                                    onClick = {
                                        if (currentRoute != Screen.Repo.route) {
                                            navController.navigate(Screen.Repo.route) {
                                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    icon = { Icon(Icons.Default.Code, contentDescription = "Repositories") },
                                    label = { Text("Repos") }
                                )

                                // 🔖 Bookmarks Tab Layout
                                NavigationBarItem(
                                    selected = currentRoute == Screen.Bookmark.route,
                                    onClick = {
                                        if (currentRoute != Screen.Bookmark.route) {
                                            navController.navigate(Screen.Bookmark.route) {
                                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    icon = { Icon(Icons.Default.Book, contentDescription = "Bookmarks") },
                                    label = { Text("Bookmarks") }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    // Forwards the scaffold padding into your host graph layout view cleanly
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}