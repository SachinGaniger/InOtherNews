package com.sachin.inothernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sachin.inothernews.R
import com.sachin.inothernews.databinding.FragmentArticleBinding
import com.sachin.inothernews.databinding.FragmentSavedNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var savedNewsBinding: FragmentSavedNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedNewsBinding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return savedNewsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}