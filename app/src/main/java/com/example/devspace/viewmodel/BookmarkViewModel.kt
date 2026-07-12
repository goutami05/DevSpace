package com.example.devspace.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.devspace.database.BookmarkDatabase
import com.example.devspace.model.news.Article
import com.example.devspace.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * AndroidViewModel because we need an application Context to open the Room database
 * (BookmarkDatabase.getDatabase requires one). Plain ViewModel has no way to get it.
 */
class BookmarkViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookmarkRepository = BookmarkRepository(
        BookmarkDatabase.getDatabase(application).bookmarkDao()
    )

    val bookmarkedArticles: StateFlow<List<Article>> = repository.allBookmarks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val bookmarkedUrls: StateFlow<Set<String>> = bookmarkedArticles
        .map { articles -> articles.mapNotNull { it.url }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    fun toggleBookmark(article: Article) {
        viewModelScope.launch {
            repository.toggleBookmark(article)
        }
    }

    fun removeBookmark(article: Article) {
        viewModelScope.launch {
            repository.removeBookmark(article)
        }
    }

    fun isBookmarked(url: String): Flow<Boolean> = repository.isBookmarked(url)
}