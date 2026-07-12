package com.example.devspace.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavController
) {

    val items = listOf(
        Screen.Home,
        Screen.Repo,
        Screen.Bookmark
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {

        items.forEach { screen ->

            NavigationBarItem(

                selected = currentRoute == screen.route,

                onClick = {

                    navController.navigate(screen.route) {

                        popUpTo(navController.graph.startDestinationId)

                        launchSingleTop = true

                    }

                },

                icon = {

                    Icon(

                        imageVector = when (screen) {
                            Screen.Home -> Icons.Default.Home
                            Screen.Repo -> Icons.Default.Storage
                            Screen.Bookmark -> Icons.Default.Bookmark
                            else -> Icons.Default.Home
                        },

                        contentDescription = screen.route

                    )

                },

                label = {

                    Text(

                        text = when (screen) {
                            Screen.Home -> "Home"
                            Screen.Repo -> "Repos"
                            Screen.Bookmark -> "Bookmarks"
                            else -> ""
                        }

                    )

                },

                colors = NavigationBarItemDefaults.colors(

                    selectedIconColor = MaterialTheme.colorScheme.primary,

                    selectedTextColor = MaterialTheme.colorScheme.primary,

                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),

                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,

                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant

                )

            )

        }

    }

}