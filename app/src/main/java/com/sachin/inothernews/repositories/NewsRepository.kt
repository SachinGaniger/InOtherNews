package com.sachin.inothernews.repositories

import com.sachin.inothernews.api.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    val newsApi : NewsApiService
) {

    suspend fun getBreakingNews(country: String, page: Int)  = newsApi.getTopHeadlines(country, page )

    suspend fun searchNews(query: String, page: Int) = newsApi.searchNews(query, page)

}