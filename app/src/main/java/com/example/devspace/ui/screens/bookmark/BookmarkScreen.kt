package com.example.devspace.ui.screens.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.devspace.ui.components.EmptyView
import com.example.devspace.ui.components.NewsCard
import com.example.devspace.ui.components.TopBar
import com.example.devspace.viewmodel.BookmarkViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BookmarkScreen(
    navController: NavController,
    bookmarkViewModel: BookmarkViewModel = viewModel()
) {

    val bookmarkedArticles by bookmarkViewModel.bookmarkedArticles.collectAsState()

    if (bookmarkedArticles.isEmpty()) {

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

            items(bookmarkedArticles, key = { it.url ?: it.hashCode().toString() }) { article ->

                NewsCard(
                    title = article.title ?: "",
                    description = article.description ?: "",
                    imageUrl = article.urlToImage ?: "https://picsum.photos/600/300",
                    source = article.source?.name ?: "Unknown Source",
                    publishedAt = article.publishedAt ?: "",
                    isBookmarked = true,
                    onBookmarkClick = {
                        bookmarkViewModel.removeBookmark(article)
                    },
                    onArticleClick = {
                        val encodedUrl = URLEncoder.encode(
                            article.url ?: "",
                            StandardCharsets.UTF_8.toString()
                        )
                        navController.navigate("webview/$encodedUrl")
                    }
                )

            }

        }

    }

}