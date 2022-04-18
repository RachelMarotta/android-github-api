package com.example.android_github_api.data

import com.example.android_github_api.model.Repositories
import retrofit2.http.GET
import retrofit2.Call

interface GithubService {

    @GET("/search/repositories?q=language:kotlin&sort=stars")
    fun getListRepositories() : Call<Repositories>
}
