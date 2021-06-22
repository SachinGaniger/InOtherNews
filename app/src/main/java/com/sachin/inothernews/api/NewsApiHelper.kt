package com.sachin.inothernews.api

import com.sachin.inothernews.data.NewsResponse
import retrofit2.Response

interface NewsApiHelper {

    suspend fun getBreakingNews(country: String, page: Int, apiKey: String) : Response<NewsResponse>

    suspend fun searchNews(query: String, page: Int, apiKey: String) : Response<NewsResponse>

}