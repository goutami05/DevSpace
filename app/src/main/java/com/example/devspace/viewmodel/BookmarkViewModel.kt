package com.example.devspace.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.devspace.database.BookmarkDatabase
import com.example.devspace.model.github.Repo
import com.example.devspace.model.news.Article
import com.example.devspace.repository.BookmarkRepository
import com.example.devspace.repository.RepoBookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application) : AndroidViewModel(application) {

    private val database = BookmarkDatabase.getDatabase(application)

    private val newsRepository = BookmarkRepository(
        database.bookmarkDao()
    )

    private val repoRepository = RepoBookmarkRepository(
        database.repoBookmarkDao()
    )

    // -------------------- News --------------------

    val bookmarkedArticles: StateFlow<List<Article>> =
        newsRepository.allBookmarks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val bookmarkedUrls: StateFlow<Set<String>> =
        bookmarkedArticles
            .map { articles ->
                articles.mapNotNull { it.url }.toSet()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptySet()
            )

    fun toggleBookmark(article: Article) {
        viewModelScope.launch {
            newsRepository.toggleBookmark(article)
        }
    }

    fun removeBookmark(article: Article) {
        viewModelScope.launch {
            newsRepository.removeBookmark(article)
        }
    }

    fun isBookmarked(url: String): Flow<Boolean> =
        newsRepository.isBookmarked(url)

    // -------------------- Repositories --------------------

    val bookmarkedRepos: StateFlow<List<Repo>> =
        repoRepository.allRepoBookmarks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val bookmarkedRepoIds: StateFlow<Set<Long>> =
        bookmarkedRepos
            .map { repos ->
                repos.map { it.id.toLong() }.toSet()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptySet()
            )

    fun toggleRepoBookmark(repo: Repo) {
        viewModelScope.launch {
            repoRepository.toggleBookmark(repo)
        }
    }

    fun removeRepoBookmark(repo: Repo) {
        viewModelScope.launch {
            repoRepository.removeBookmark(repo)
        }
    }

    fun isRepoBookmarked(id: Long): Flow<Boolean> =
        repoRepository.isBookmarked(id)
}