package com.example.devspace.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.devspace.model.news.Article
import com.example.devspace.network.NewsApi
import com.example.devspace.paging.NewsPagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val newsApi: NewsApi) {
    fun getNewsStream(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(newsApi) }
        ).flow
    }
}