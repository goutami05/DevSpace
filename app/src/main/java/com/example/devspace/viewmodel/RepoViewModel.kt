package com.example.devspace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.devspace.network.RetrofitInstance
import com.example.devspace.paging.RepoPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class RepoViewModel : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val githubReposStream = _searchQuery
        .debounce(500) // 🌟 CRITICAL: Prevents the API rate limit block by waiting until you stop typing
        .flatMapLatest { language ->
            val apiQuery = if (language.trim().isBlank()) {
                "stars:>1000"
            } else {
                "stars:>1000 language:${language.trim()}"
            }

            Pager(
                config = PagingConfig(
                    pageSize = 15,
                    enablePlaceholders = false,
                    initialLoadSize = 15
                ),
                pagingSourceFactory = { RepoPagingSource(RetrofitInstance.githubApi, apiQuery) }
            ).flow
        }.cachedIn(viewModelScope)

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}