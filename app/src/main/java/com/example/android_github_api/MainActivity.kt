package com.example.android_github_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_github_api.data.ApiService
import com.example.android_github_api.data.GithubService
import com.example.android_github_api.model.Repositories
import com.example.android_github_api.model.RepositoryItem
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerview: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var repositoryAdapter: RepositoryAdapter
    lateinit var listRepositories: MutableList<RepositoryItem>
    lateinit var progressBar: ProgressBar

    var page: Int = 1
    var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.rv_repositories)
        layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerview.layoutManager = layoutManager
        listRepositories = arrayListOf()
        progressBar = findViewById(R.id.progressbar)

        getRepositories(page)
        scrollPage()
    }

    private fun scrollPage() {

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, firstPosition: Int, lastPosition: Int) {

                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                val total = repositoryAdapter.itemCount

                if (!isLoading) {

                    if ((visibleItemCount + lastVisibleItem ) >= total) {
                        page++
                        getRepositories(page)
                    }
                }
                super.onScrolled(recyclerView, firstPosition, lastPosition)
            }
        })

    }

    private fun getRepositories(page: Int) {

        isLoading = true
        progressBar.visibility = View.VISIBLE

        val request = ApiService.buildService(GithubService::class.java)
        val call = request.getListRepositories(page)

        call.enqueue(object : Callback<Repositories> {

            override fun onResponse(
                call: Call<Repositories>,
                response: Response<Repositories>
            ) {
                if(response.isSuccessful) {
                    listRepositories.addAll(response.body()!!.items)
                    repositoryAdapter = RepositoryAdapter(listRepositories)

                    if(page > 1) {
                        val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
                        repositoryAdapter.notifyItemInserted(lastVisible)
                    } else {
                        recyclerview.apply {
                            setHasFixedSize(true)
                            adapter = repositoryAdapter
                        }
                    }
                }
                isLoading = false
                progressBar.visibility = View.GONE
            }

            override fun onFailure(
                call: Call<Repositories>,
                t: Throwable?
            ) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}