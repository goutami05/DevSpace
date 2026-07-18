package com.example.devspace.model.news

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    var id: Int? = null,
    // Dev.to stores user info inside a user object, or we can fallback to the target name field
    @SerializedName("user") val authorUser: AuthorUser? = null,
    val content: String? = null,
    val description: String? = null,

    @SerializedName("published_at")
    val publishedAt: String? = null,

    val title: String? = null,
    val url: String? = null,

    // 🌟 FIX: Maps Dev.to's image property back to your existing UI field name seamlessly!
    @SerializedName("cover_image")
    val urlToImage: String? = null
) : Serializable {
    // Helper property to maintain backwards compatibility with your existing UI author text display
    val author: String?
        get() = authorUser?.name ?: "Unknown Developer"
}

// Simple internal helper class to map the nested user display structure returned by Dev.to
data class AuthorUser(
    val name: String?
) : Serializable