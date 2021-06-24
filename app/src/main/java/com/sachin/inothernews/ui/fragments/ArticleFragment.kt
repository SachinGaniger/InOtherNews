package com.sachin.inothernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sachin.inothernews.R
import com.sachin.inothernews.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var articleBinding: FragmentArticleBinding
    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleBinding = FragmentArticleBinding.inflate(inflater, container, false)
        return articleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = args.article
        articleBinding.webView.apply {
            ////this is make sure that links loads in app rather than chrome
            webViewClient = WebViewClient()
            loadUrl(url)
        }

    }
}