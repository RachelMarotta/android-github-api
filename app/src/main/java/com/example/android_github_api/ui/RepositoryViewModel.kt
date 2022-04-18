package com.example.android_github_api.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_github_api.data.ApiService
import com.example.android_github_api.data.GithubService
import com.example.android_github_api.model.Repositories
import com.example.android_github_api.model.RepositoryItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel : ViewModel() {

    private val _repositories = MutableLiveData<List<RepositoryItem>>()
    val repositories: LiveData<List<RepositoryItem>>
        get() = _repositories

    fun fetchRepositories() {
        if(repositories.value == null || repositories.value?.isEmpty() == true) {
            request()
        }
    }

    private fun request() {
        val request = ApiService.buildService(GithubService::class.java)
        val call = request.getListRepositories()

        call.enqueue(object : Callback<Repositories> {

            override fun onResponse(
                call: Call<Repositories>,
                response: Response<Repositories>
            ) {
                if (response.isSuccessful) {
                    response.body()?.items.let {
                        _repositories.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Repositories>, t: Throwable) {
                Log.d("ERROR", "onFailure")
            }
        })
    }
}