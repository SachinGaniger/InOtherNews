
package com.sachin.inothernews.ui.fragments

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.inothernews.R
import com.sachin.inothernews.databinding.FragmentBreakingNewsBinding
import com.sachin.inothernews.ui.adapters.NewsAdapter
import com.sachin.inothernews.ui.viewModels.NewsViewModel
import com.sachin.inothernews.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var breakingNewsBinding: FragmentBreakingNewsBinding
    private val viewModel : NewsViewModel by viewModels()
    private var newsAdapter: NewsAdapter? = null

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


        val countryListMap = mapOf("in" to "India", "us" to "USA", "gb" to "UK", "fr" to "France", "de" to "Germany")

        val countryList = countryListMap.values.toList()

        val spinnerAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, countryList).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner

        }

        breakingNewsBinding.spCountry.adapter = spinnerAdapter

        breakingNewsBinding.spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Timber.d(countryListMap.keys.toList()[position])
                viewModel.getBreakingNews(countryListMap.keys.toList()[position], 1)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        observeNews()
        breakingNewsBinding.rvBreakingNews.layoutManager = LinearLayoutManager(activity)
        newsAdapter = NewsAdapter()
        breakingNewsBinding.rvBreakingNews.adapter = newsAdapter



        newsAdapter?.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("article", it.url)
            }
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment, bundle)
        }

//        setUpRecyclerView()
    }

    private fun observeNews() {
        viewModel.getNewsData().observe(viewLifecycleOwner, Observer { newsResponse ->

            when(newsResponse){
                is Resource.Success ->{
                    hideProgressbar()
                    newsResponse.data?.let {
//                        newsAdapter = NewsAdapter(it.articles)

                        newsAdapter?.differ?.submitList(it.articles)

//                        newsAdapter!!.notifyDataSetChanged()

                    }
                }

                is Resource.Loading ->{
                    showProgressBar()
                }

                is Resource.Error ->{
                    hideProgressbar()
                    newsResponse.message?.let {message ->
                        showErrorMessage(message)

                    }
                }
            }


        })
    }

    private fun showErrorMessage(message: String) {
        breakingNewsBinding.tvErrorMessage.text = message
    }

    private fun showProgressBar() {
        breakingNewsBinding.paginationProgressBar.visibility = VISIBLE
    }

    private fun hideProgressbar() {
        breakingNewsBinding.apply {
            paginationProgressBar.visibility = INVISIBLE
        }
    }

    private fun setUpRecyclerView() {

        breakingNewsBinding.apply {

            rvBreakingNews.layoutManager = LinearLayoutManager(activity)
            rvBreakingNews.adapter = newsAdapter

        }
    }



}