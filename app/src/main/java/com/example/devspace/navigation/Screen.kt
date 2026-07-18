package com.example.devspace.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Repo : Screen("repo")
    object Bookmark : Screen("bookmark")
    object WebView : Screen("webview/{url}") {
        fun createRoute(url: String): String {
            val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
            return "webview/$encodedUrl"
        }
    }
}
