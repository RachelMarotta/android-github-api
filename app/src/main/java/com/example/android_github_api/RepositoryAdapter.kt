package com.example.android_github_api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_github_api.model.RepositoryItem

class RepositoryAdapter(private val listRepositories: List<RepositoryItem>) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.repository_card_item, parent, false)

        return RepositoryViewHolder(card)
    }

    override fun getItemCount(): Int {
        return listRepositories.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        return holder.bind(listRepositories[position])
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repositoryPhoto: ImageView = itemView.findViewById(R.id.repository_photo)
        private val repositoryName: TextView = itemView.findViewById(R.id.repository_name)
        private val repositoryDescription: TextView = itemView.findViewById(R.id.repository_description)
        private val repositoryAuthor: TextView = itemView.findViewById(R.id.repository_author)
        private val repositoryStars: TextView = itemView.findViewById(R.id.repository_stars)
        private val repositoryForks: TextView = itemView.findViewById(R.id.repository_forks)

        fun bind(item: RepositoryItem) {
            Glide.with(itemView.context).load(item.owner.avatar_url).into(repositoryPhoto)
            repositoryName.text = item.name
            repositoryDescription.text = item.description
            repositoryAuthor.text = item.owner.login
            repositoryStars.text = item.stargazers_count.toString()
            repositoryForks.text = item.forks_count.toString()
        }
    }
}