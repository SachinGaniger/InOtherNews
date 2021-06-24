package com.sachin.inothernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sachin.inothernews.R
import com.sachin.inothernews.databinding.FragmentArticleBinding
import com.sachin.inothernews.databinding.FragmentSearchNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var searchNewsBinding: FragmentSearchNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchNewsBinding = FragmentSearchNewsBinding.inflate(inflater, container, false)
        return searchNewsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}