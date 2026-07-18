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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.devspace.navigation.Screen // 🌟 Double check this import is present
import com.example.devspace.ui.components.NewsCard
import com.example.devspace.ui.components.TopBar
import com.example.devspace.viewmodel.BookmarkViewModel
import com.example.devspace.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(),
    bookmarkViewModel: BookmarkViewModel = viewModel()
) {
    val lazyArticles = homeViewModel.newsArticlesStream.collectAsLazyPagingItems()
    val isRefreshing = lazyArticles.loadState.refresh is LoadState.Loading
    val bookmarkedUrls by bookmarkViewModel.bookmarkedUrls.collectAsState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { lazyArticles.refresh() },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopBar(
                    title = "Tech Insights",
                    subtitle = "Your daily dose of system updates and breaking tech 🚀"
                )
            }

            items(count = lazyArticles.itemCount) { index ->
                val article = lazyArticles[index]
                if (article != null) {
                    NewsCard(
                        title = article.title ?: "",
                        description = article.description ?: "",
                        imageUrl = article.urlToImage ?: "https://picsum.photos/600/300",
                        source = article.author ?: "Unknown Source",
                        publishedAt = article.publishedAt ?: "",
                        isBookmarked = bookmarkedUrls.contains(article.url),
                        onBookmarkClick = {
                            bookmarkViewModel.toggleBookmark(article)
                        },
                        onArticleClick = {
                            val originalUrl = article.url
                            if (!originalUrl.isNullOrBlank()) {
                                navController.navigate(Screen.WebView.createRoute(originalUrl))
                            }
                        }
                    )
                }
            }

            if (lazyArticles.loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}