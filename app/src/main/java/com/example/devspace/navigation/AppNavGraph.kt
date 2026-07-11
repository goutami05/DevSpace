package com.example.devspace.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.devspace.ui.screens.bookmark.BookmarkScreen
import com.example.devspace.ui.screens.home.HomeScreen
import com.example.devspace.ui.screens.repo.RepoScreen
import com.example.devspace.ui.screens.splash.SplashScreen
import com.example.devspace.ui.screens.webview.WebViewScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(

        bottomBar = {

            if (
                currentRoute == Screen.Home.route ||
                currentRoute == Screen.Repo.route ||
                currentRoute == Screen.Bookmark.route
            ) {
                BottomBar(navController)
            }

        }

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Splash.route) {
                SplashScreen(onSplashFinished = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                })
            }

            composable(Screen.Home.route) {
                HomeScreen(navController)
            }

            composable(Screen.Repo.route) {
                RepoScreen(navController)
            }

            composable(Screen.Bookmark.route) {
                BookmarkScreen(navController)
            }

            composable(
                route = Screen.WebView.route,
                arguments = listOf(
                    navArgument("url") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val url =
                    backStackEntry.arguments?.getString("url") ?: ""

                WebViewScreen(url, navController)

            }

        }

    }

}