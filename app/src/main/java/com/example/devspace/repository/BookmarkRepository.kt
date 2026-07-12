package com.example.devspace.repository

import com.example.devspace.database.BookmarkDao
import com.example.devspace.database.BookmarkEntity
import com.example.devspace.database.toArticle
import com.example.devspace.database.toBookmarkEntity
import com.example.devspace.model.news.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {

    val allBookmarks: Flow<List<Article>> =
        bookmarkDao.getAllBookmarks().map { entities -> entities.map(BookmarkEntity::toArticle) }

    suspend fun addBookmark(article: Article) {
        article.toBookmarkEntity()?.let { bookmarkDao.insertBookmark(it) }
    }

    suspend fun removeBookmark(article: Article) {
        val url = article.url ?: return
        bookmarkDao.deleteBookmarkByUrl(url)
    }

    suspend fun toggleBookmark(article: Article) {
        val url = article.url ?: return
        if (bookmarkDao.getBookmarkByUrl(url) != null) {
            bookmarkDao.deleteBookmarkByUrl(url)
        } else {
            addBookmark(article)
        }
    }

    fun isBookmarked(url: String): Flow<Boolean> = bookmarkDao.isBookmarked(url)

    suspend fun clearAllBookmarks() = bookmarkDao.clearAllBookmarks()
}