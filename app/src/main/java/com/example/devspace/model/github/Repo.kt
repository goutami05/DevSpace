package com.example.devspace.model.github

data class Repo(
    val id: Int,
    val name: String,
    val full_name: String,
    val description: String?,
    val html_url: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner: Owner,
    val language: String?
)
