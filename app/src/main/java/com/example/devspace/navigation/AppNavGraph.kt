package com.example.devspace.navigation

import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.devspace.ui.screens.bookmark.BookmarkScreen
import com.example.devspace.ui.screens.home.HomeScreen
import com.example.devspace.ui.screens.repo.RepoScreen
import com.example.devspace.ui.screens.splash.SplashScreen
import com.example.devspace.ui.screens.webview.WebViewScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier // 🌟 Accept structural inner paddings here
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier // 🌟 Forward modifier properties safely
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onSplashFinished = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.Repo.route) {
            RepoScreen(navController = navController)
        }

        composable(Screen.Bookmark.route) {
            BookmarkScreen(navController = navController)
        }

        composable(
            route = "webview/{url}",
            arguments = listOf(
                navArgument("url") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val decodedUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
            WebViewScreen(url = decodedUrl, navController = navController)
        }
    }
}