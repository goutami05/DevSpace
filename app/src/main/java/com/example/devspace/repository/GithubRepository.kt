package com.example.devspace.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.devspace.model.github.Repo
import com.example.devspace.network.GithubApi
import com.example.devspace.paging.RepoPagingSource
import kotlinx.coroutines.flow.Flow

class GithubRepository(private val githubApi: GithubApi) {

    /**
     * Streams an infinitely pageable list of trending GitHub repositories.
     */
    fun getTrendingRepoStream(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,          // Loads 20 repositories per page request
                enablePlaceholders = false // Prevents showing empty placeholder blocks while loading
            ),
            pagingSourceFactory = { RepoPagingSource(githubApi, "stars:>1000") }
        ).flow
    }
}