package com.example.devspace.ui.screens.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.devspace.ui.components.EmptyView
import com.example.devspace.ui.components.NewsCard
import com.example.devspace.ui.components.TopBar

data class BookmarkArticle(
    val title: String,
    val description: String,
    val image: String,
    val source: String,
    val date: String
)

@Composable
fun BookmarkScreen(navController: NavController) {

    val bookmarkList = listOf(

        BookmarkArticle(
            title = "Android 17 Released",
            description = "Google has officially announced Android 17 with several AI-powered features.",
            image = "https://picsum.photos/603/300",
            source = "Android Developers",
            date = "2 hours ago"
        ),

        BookmarkArticle(
            title = "Jetpack Compose 2.0",
            description = "Compose becomes faster and more efficient.",
            image = "https://picsum.photos/604/300",
            source = "Google",
            date = "Yesterday"
        )

    )

    if (bookmarkList.isEmpty()) {

        EmptyView(
            title = "No Bookmarks Yet",
            message = "Save your favourite articles and read them later."
        )

    } else {

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),

            contentPadding = PaddingValues(vertical = 12.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            item {

                TopBar(
                    title = "Bookmarks",
                    subtitle = "Your saved articles"
                )

            }

            items(bookmarkList) { article ->

                NewsCard(
                    title = article.title,
                    description = article.description,
                    imageUrl = article.image,
                    source = article.source,
                    publishedAt = article.date,
                    isBookmarked = true,
                    onBookmarkClick = {},
                    onArticleClick = {}
                )

            }

        }

    }

}