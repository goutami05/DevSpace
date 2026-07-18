package com.example.devspace.network

import com.example.devspace.model.news.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    // 🌟 Fetches global dev/tech posts matching your Article model fields
    @GET("api/articles")
    suspend fun getBreakingNews(
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int = 20
    ): List<Article>
}