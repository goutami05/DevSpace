package com.example.devspace.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.devspace.model.news.Article
import com.example.devspace.network.NewsApi
import com.example.devspace.utils.Constants

class NewsPagingSource(
    private val newsApi: NewsApi
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        return try {
            val response = newsApi.getBreakingNews(pageNumber = position, apiKey = Constants.NEWS_API_KEY)
            LoadResult.Page(
                data = response.articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.articles.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}