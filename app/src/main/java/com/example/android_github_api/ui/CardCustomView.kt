package com.example.android_github_api.ui

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.android_github_api.R
import com.example.android_github_api.model.RepositoryItem

class CardCustomView(context: Context) : LinearLayout(context) {

    private val photo by lazy { findViewById<ImageView>(R.id.repository_photo) }
    private val author by lazy { findViewById<TextView>(R.id.repository_author) }
    private val repositoryName by lazy { findViewById<TextView>(R.id.repository_name) }
    private val description by lazy { findViewById<TextView>(R.id.repository_description) }
    private val stars by lazy { findViewById<TextView>(R.id.repository_stars) }
    private val forks by lazy { findViewById<TextView>(R.id.repository_forks) }
    
    init {
        orientation = VERTICAL
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        inflate(context, R.layout.repository_card_item, this)
    }
    
    fun setup(item: RepositoryItem) {
        Glide.with(context).load(item.owner.avatar_url).into(photo)
        author.text = item.owner.login
        repositoryName.text = item.name
        description.text = item.description
        stars.text = item.stargazers_count.toString()
        forks.text = item.forks_count.toString()
    }
}