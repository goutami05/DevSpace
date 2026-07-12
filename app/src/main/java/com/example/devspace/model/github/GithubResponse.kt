package com.example.devspace.model.github

data class GithubResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repo>
)
