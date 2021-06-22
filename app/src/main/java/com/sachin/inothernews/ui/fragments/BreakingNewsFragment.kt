package com.sachin.inothernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.inothernews.R
import com.sachin.inothernews.databinding.FragmentBreakingNewsBinding
import com.sachin.inothernews.ui.adapters.NewsAdapter
import com.sachin.inothernews.ui.viewModels.NewsViewModel

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var breakingNewsBinding: FragmentBreakingNewsBinding
    private val viewModel : NewsViewModel by viewModels()
    private val newsAdapter: NewsAdapter? = null

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

        setUpRecyclerView()

        viewModel.getBreakingNews("In", 1)

        observeNews()
    }

    private fun observeNews() {
        viewModel.
    }

    private fun setUpRecyclerView() {

        breakingNewsBinding.apply {

            rvBreakingNews.layoutManager = LinearLayoutManager(activity)

        }
    }
}