package com.example.android_github_api.model

data class RepositoryItem (
    val name: String,
    val description: String,
    val forks_count: Int,
    val stargazers_count: Int,
    var owner: RepositoryOwner
)