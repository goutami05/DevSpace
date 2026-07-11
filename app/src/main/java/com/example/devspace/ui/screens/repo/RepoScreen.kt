package com.example.devspace.ui.screens.repo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.devspace.ui.components.RepoCard
import com.example.devspace.ui.components.SearchBar
import com.example.devspace.ui.components.TopBar

data class DummyRepo(
    val name: String,
    val description: String,
    val owner: String,
    val language: String,
    val stars: Int,
    val forks: Int
)

@Composable
fun RepoScreen(navController: NavController) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    val repoList = listOf(

        DummyRepo(
            "Retrofit",
            "A type-safe HTTP client for Android and Kotlin.",
            "Square",
            "Kotlin",
            45000,
            8500
        ),

        DummyRepo(
            "Jetpack Compose",
            "Android's modern toolkit for building native UI.",
            "Google",
            "Kotlin",
            39000,
            7200
        ),

        DummyRepo(
            "Coil",
            "Image loading library for Android backed by Kotlin Coroutines.",
            "Coil-KT",
            "Kotlin",
            12000,
            700
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
                title = "GitHub Explorer",
                subtitle = "Discover trending repositories"
            )

        }

        item {

            SearchBar(
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it
                },
                placeholder = "Search by programming language"
            )

        }

        item {

            Text(
                text = "🔥 Trending Repositories",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

        }

        items(repoList) { repo ->

            RepoCard(
                repoName = repo.name,
                description = repo.description,
                owner = repo.owner,
                language = repo.language,
                stars = repo.stars,
                forks = repo.forks,
                onRepoClick = {}
            )

        }

    }

}