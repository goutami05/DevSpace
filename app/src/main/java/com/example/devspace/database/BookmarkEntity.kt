package com.example.devspace.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.devspace.model.news.Article
import com.example.devspace.model.news.AuthorUser

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
        // 🌟 Maps your helper string property out to the database row string
        author = author,
        sourceName = null,
        urlToImage = urlToImage,
        publishedAt = publishedAt
    )
}

fun BookmarkEntity.toArticle(): Article {
    return Article(
        id = null,
        // 🌟 Maps the database row string back to the nested model object configuration
        authorUser = author?.let { AuthorUser(it) },
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}