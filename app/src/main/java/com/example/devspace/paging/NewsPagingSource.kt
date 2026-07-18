package com.example.devspace.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.devspace.model.news.Article
import com.example.devspace.network.NewsApi

class NewsPagingSource(
    private val newsApi: NewsApi
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        return try {
            // 🌟 Fetches the direct JSON array from Dev.to cleanly
            val articlesList = newsApi.getBreakingNews(pageNumber = position)

            LoadResult.Page(
                data = articlesList,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (articlesList.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            Log.e("NewsPagingSource", "Network connection failed fetching tech news", exception)
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // 🌟 FIX: Force-returns null so swipe-to-refresh completely clears the old cache and drops down new articles at page 1
        return null
    }
}