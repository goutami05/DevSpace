
package com.example.devspace.network

import com.example.devspace.model.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}