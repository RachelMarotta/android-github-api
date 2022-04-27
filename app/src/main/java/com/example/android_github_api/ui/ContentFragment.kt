package com.example.android_github_api.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.android_github_api.R

class ContentFragment : Fragment(R.layout.content_fragment) {

    private val args: ContentFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.description_github).text = args.contentArgs
    }

}