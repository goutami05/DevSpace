package com.example.devspace.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Repo : Screen("repo")
    object Bookmark : Screen("bookmark")
    object WebView : Screen("webview/{url}") {
        fun createRoute(url: String): String {
            return "webview/$url"
        }
    }
}