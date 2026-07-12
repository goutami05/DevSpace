package com.example.devspace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.devspace.model.news.Article
import com.example.devspace.network.RetrofitInstance
import com.example.devspace.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel : ViewModel() {

    // 1. Manually initialize the repository using your Retrofit instance
    private val repository = NewsRepository(RetrofitInstance.newsApi)

    // 2. Fetch the stream and cache it in the viewModelScope so it survives screen rotations
    val newsArticlesStream: Flow<PagingData<Article>> = repository.getNewsStream()
        .cachedIn(viewModelScope)
}