package com.example.devspace.ui.screens.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.devspace.navigation.Screen
import com.example.devspace.ui.components.EmptyView
import com.example.devspace.ui.components.NewsCard
import com.example.devspace.ui.components.RepoCard
import com.example.devspace.ui.components.TopBar
import com.example.devspace.viewmodel.BookmarkViewModel

@Composable
fun BookmarkScreen(
    navController: NavController,
    bookmarkViewModel: BookmarkViewModel = viewModel()
) {

    val bookmarkedArticles by bookmarkViewModel.bookmarkedArticles.collectAsState()

    val bookmarkedRepos by bookmarkViewModel.bookmarkedRepos.collectAsState()

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        TopBar(
            title = "Bookmarks",
            subtitle = "Your saved items",
            navController = navController
        )

        TabRow(
            selectedTabIndex = selectedTab
        ) {

            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = {
                    Text("News")
                }
            )

            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = {
                    Text("Repositories")
                }
            )

        }

        if (selectedTab == 0) {

            if (bookmarkedArticles.isEmpty()) {

                EmptyView(
                    title = "No News Bookmarks",
                    message = "Bookmark news articles to read them later."
                )

            } else {

                LazyColumn(
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        bookmarkedArticles,
                        key = { it.url ?: it.hashCode().toString() }
                    ) { article ->

                        NewsCard(

                            title = article.title ?: "",

                            description = article.description ?: "",

                            imageUrl = article.urlToImage
                                ?: "https://picsum.photos/600/300",

                            source = article.author ?: "Unknown",

                            publishedAt = article.publishedAt ?: "",

                            isBookmarked = true,

                            onBookmarkClick = {

                                bookmarkViewModel.removeBookmark(article)

                            },

                            onArticleClick = {

                                article.url?.let {

                                    navController.navigate(
                                        Screen.WebView.createRoute(it)
                                    )

                                }

                            }

                        )

                    }

                }

            }

        } else {
            if (bookmarkedRepos.isEmpty()) {

                EmptyView(
                    title = "No Repository Bookmarks",
                    message = "Bookmark repositories to access them quickly."
                )

            } else {

                LazyColumn(
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        bookmarkedRepos,
                        key = { it.id }
                    ) { repo ->

                        RepoCard(

                            repo = repo,

                            isBookmarked = true,

                            onBookmarkClick = {

                                bookmarkViewModel.removeRepoBookmark(repo)

                            },

                            onRepoClick = {

                                navController.navigate(
                                    Screen.WebView.createRoute(repo.html_url)
                                )

                            }

                        )

                    }

                }

            }

        }

    }

}
