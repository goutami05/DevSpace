package com.example.devspace.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.devspace.model.news.Article
import com.example.devspace.model.news.Source

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val url: String,
    val title: String?,
    val description: String?,
    val content: String?,
    val author: String?,
    val sourceName: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val bookmarkedAt: Long = System.currentTimeMillis()
)

fun Article.toBookmarkEntity(): BookmarkEntity? {
    val articleUrl = url ?: return null
    return BookmarkEntity(
        url = articleUrl,
        title = title,
        description = description,
        content = content,
        author = author,
        sourceName = source?.name,
        urlToImage = urlToImage,
        publishedAt = publishedAt
    )
}

fun BookmarkEntity.toArticle(): Article {
    return Article(
        id = null,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = sourceName?.let { Source(id = null, name = it) },
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}


