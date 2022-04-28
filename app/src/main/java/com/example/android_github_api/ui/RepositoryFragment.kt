package com.example.android_github_api.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_github_api.R

class RepositoryFragment : Fragment(R.layout.repository_fragment) {

    private val viewModel by lazy {
        ViewModelProvider(this).get(RepositoryViewModel::class.java)
    }
    private lateinit var recycler: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_repositories)
        recycler.layoutManager = LinearLayoutManager(context)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.fetchRepositories()
        viewModel.repositories.observe(viewLifecycleOwner) {
            recycler.apply {

                recycler.adapter = RepositoryAdapter(it) { content ->
                    findNavController().navigate(
                        RepositoryFragmentDirections.actionRepositoryFragmentToContentFragment(
                            content
                        )
                    )
                }

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