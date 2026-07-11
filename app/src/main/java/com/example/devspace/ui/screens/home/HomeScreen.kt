package com.example.devspace.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.devspace.navigation.Screen
import com.example.devspace.ui.components.NewsCard
import com.example.devspace.ui.components.SearchBar
import com.example.devspace.ui.components.TopBar

data class DummyArticle(
    val title: String,
    val description: String,
    val image: String,
    val source: String,
    val date: String,
    val url: String
)

@Composable
fun HomeScreen(
    navController: NavController
) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    val newsList = listOf(

        DummyArticle(
            "Android 17 Released",
            "Google has officially announced Android 17 with several AI powered features.",
            "https://picsum.photos/600/300",
            "Android Developers",
            "2 hours ago",
            "https://developer.android.com"
        ),

        DummyArticle(
            "Jetpack Compose 2.0",
            "Compose becomes faster and more efficient for Android UI development.",
            "https://picsum.photos/601/300",
            "Google",
            "Yesterday",
            "https://developer.android.com"
        ),

        DummyArticle(
            "Kotlin 2.2 Released",
            "JetBrains introduces exciting new language improvements.",
            "https://picsum.photos/602/300",
            "JetBrains",
            "3 days ago",
            "https://kotlinlang.org"
        )

    )

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),

        contentPadding = PaddingValues(vertical = 12.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        item {

            TopBar(
                title = "DevSpace",
                subtitle = "Stay updated with technology 🚀"
            )

        }

        item {

            SearchBar(
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it
                },
                placeholder = "Search Tech News"
            )

        }

        item {

            Text(
                text = "🔥 Trending News",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }

        items(newsList) { article ->

            NewsCard(
                title = article.title,
                description = article.description,
                imageUrl = article.image,
                source = article.source,
                publishedAt = article.date,
                isBookmarked = false,
                onBookmarkClick = {},
                onArticleClick = {
                    navController.navigate(
                        Screen.WebView.createRoute(article.url)
                    )
                }
            )

        }

    }

}
