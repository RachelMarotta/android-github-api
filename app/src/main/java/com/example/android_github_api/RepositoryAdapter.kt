package com.example.android_github_api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_github_api.model.RepositoryItem

private const val FORKS = 1000
private const val FORKS_VIEW_TYPE = 2
private const val REPOSITORY_VIEW_TYPE = 1

class RepositoryAdapter(listRepositories: List<RepositoryItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ViewBase>()

    init {
        listRepositories.forEach {
            items.add(RepositoryViewType(it))

            if (it.forks_count > FORKS) {
                items.add(ForksViewType())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == REPOSITORY_VIEW_TYPE) {
            RepositoryViewHolder(CardCustomView(parent.context))
        } else {
            ForksViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.forks_quantity, parent,false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryViewHolder) {
            holder.bindItem((items[position] as RepositoryViewType).item)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].viewType

    class ForksViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class RepositoryViewHolder(private val view: CardCustomView) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: RepositoryItem) = view.setup(item)
    }
}

abstract class ViewBase(val viewType: Int)
class ForksViewType : ViewBase(FORKS_VIEW_TYPE)
class RepositoryViewType(val item: RepositoryItem) : ViewBase(REPOSITORY_VIEW_TYPE)