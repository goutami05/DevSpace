package com.example.devspace.network

import com.example.devspace.model.github.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun searchTrendingRepos(
        @Query("q") query: String = "language:kotlin",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
        @Query("page") pageNumber: Int,
        @Query("per_page") perPage: Int = 20
    ): GithubResponse
}