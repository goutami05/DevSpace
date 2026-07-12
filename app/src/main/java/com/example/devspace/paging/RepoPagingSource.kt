package com.example.devspace.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.devspace.model.github.Repo
import com.example.devspace.network.GithubApi

class RepoPagingSource(
    private val githubApi: GithubApi
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        // 1. Default to Page 1 on initial load request
        val position = params.key ?: 1

        return try {
            // 2. Fetch the specified page size requirement from the GitHub API network stream
            val response = githubApi.searchTrendingRepos(
                pageNumber = position,
                perPage = params.loadSize
            )

            val repos = response.items

            // 3. Return the calculated pagination window parameters
            LoadResult.Page(
                data = repos,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            // 4. Safely forward errors to the ViewModel without crashing the app environment
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        // 5. Calculate anchor alignment to maintain user position on swipe-to-refresh
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}