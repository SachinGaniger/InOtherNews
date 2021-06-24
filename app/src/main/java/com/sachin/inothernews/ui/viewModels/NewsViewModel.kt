package com.sachin.inothernews.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.inothernews.data.Article
import com.sachin.inothernews.data.NewsResponse
import com.sachin.inothernews.repositories.NewsRepository
import com.sachin.inothernews.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(){
    private val newsData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()


//    init {
//        getBreakingNews(country, page)
//    }

    public fun getBreakingNews(country: String, page: Int) =
        viewModelScope.launch {
            newsData.postValue(Resource.Loading())
            val newsResponse = handleBreakingNews(repository.getBreakingNews(country, page))
            newsData.postValue(newsResponse)
        }

    fun handleBreakingNews(response: Response<NewsResponse>): Resource<NewsResponse> {

        if(response.isSuccessful){
            response.body()?.let {newsResponse ->
                return Resource.Success(newsResponse)
            }
        }
        return Resource.Error(response.message())

    }

    fun getNewsData(): LiveData<Resource<NewsResponse>>{
        return newsData
    }


}