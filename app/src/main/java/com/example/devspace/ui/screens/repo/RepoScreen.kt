package com.example.devspace.ui.screens.repo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.example.devspace.navigation.Screen
import com.example.devspace.ui.components.RepoCard
import com.example.devspace.ui.components.TopBar
import com.example.devspace.viewmodel.RepoViewModel

@Composable
fun RepoScreen(
    navController: NavController,
    repoViewModel: RepoViewModel = viewModel()
) {
    val lazyRepos = repoViewModel.githubReposStream.collectAsLazyPagingItems()
    val searchQuery by repoViewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(
            title = "Trending Repositories",
            subtitle = "Explore top-tier open source innovations worldwide 🌍",
            navController = navController
        )

        // 🌟 Input Search Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { repoViewModel.updateSearchQuery(it) },
            label = { Text("Search by programming language (e.g., Kotlin)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            singleLine = true
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count = lazyRepos.itemCount) { index ->
                val repo = lazyRepos[index]
                if (repo != null) {
                    RepoCard(
                        repo = repo,
                        onRepoClick = {
                            val originalUrl = repo.html_url
                            if (!originalUrl.isNullOrBlank()) {
                                navController.navigate(Screen.WebView.createRoute(originalUrl))
                            }
                        }
                    )
                }
            }

            if (lazyRepos.loadState.append is LoadState.Loading) {
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