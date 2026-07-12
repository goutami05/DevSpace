package com.example.devspace.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.devspace.navigation.Screen
import com.example.devspace.ui.components.NewsCard
import com.example.devspace.ui.components.SearchBar
import com.example.devspace.ui.components.TopBar
import com.example.devspace.viewmodel.HomeViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.example.devspace.viewmodel.BookmarkViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(), // 1. Pass down your ViewModel instance
    bookmarkViewModel: BookmarkViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }

    // 2. Collect your live Paging 3 items stream
    val lazyArticles = homeViewModel.newsArticlesStream.collectAsLazyPagingItems()
    val bookmarkedUrls by bookmarkViewModel.bookmarkedUrls.collectAsState()

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
                onQueryChange = { searchQuery = it },
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

        // 3. Render your live network elements using lazyArticles.itemCount
        items(count = lazyArticles.itemCount) { index ->
            val article = lazyArticles[index]
            if (article != null) {
                NewsCard(
                    title = article.title ?: "",
                    description = article.description ?: "",
                    imageUrl = article.urlToImage ?: "https://picsum.photos/600/300",
                    source = article.source?.name ?: "Unknown Source",
                    publishedAt = article.publishedAt ?: "",
                    isBookmarked = article.url != null && bookmarkedUrls.contains(article.url),
                    onBookmarkClick = {
                        bookmarkViewModel.toggleBookmark(article)
                    },
                    onArticleClick = {
                        val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                        navController.navigate("webview/$encodedUrl")
                    }
                )
            }
        }

        // 4. Show a loading wheel at the bottom of your feed when downloading the next page
        if (lazyArticles.loadState.append is LoadState.Loading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    // 5. Show a fullscreen loading wheel on initial launch before data hits the cache
    if (lazyArticles.loadState.refresh is LoadState.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}