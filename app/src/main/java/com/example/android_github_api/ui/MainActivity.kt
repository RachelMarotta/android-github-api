package com.example.android_github_api.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_github_api.R

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.rv_repositories) }
    private val viewModel by lazy {
        ViewModelProvider(this).get(RepositoryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.fetchRepositories()

        viewModel.repositories.observe(this) {
            recyclerView.apply {
                recyclerView.adapter = RepositoryAdapter(it)
                setHasFixedSize(true)
                addItemDecoration(
                    ItemDecoration(
                        resources.getDimensionPixelOffset(R.dimen.dimen_16),
                        resources.getDimensionPixelOffset(R.dimen.dimen_10)
                    )
                )
            }
        }
    }
}