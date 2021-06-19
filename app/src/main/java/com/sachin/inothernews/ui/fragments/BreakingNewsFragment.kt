package com.sachin.inothernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sachin.inothernews.R
import com.sachin.inothernews.databinding.FragmentBreakingNewsBinding

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var breakingNewsBinding: FragmentBreakingNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        breakingNewsBinding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return breakingNewsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}