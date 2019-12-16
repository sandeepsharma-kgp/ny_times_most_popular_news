package com.example.nyt_mostpopular.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyt_mostpopular.NewsApi
import com.example.nyt_mostpopular.NewsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _news = MutableLiveData<List<NewsModel>>()
    val news: LiveData<List<NewsModel>>
        get() = _news

    init {
        getNewsList()
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private fun getNewsList() {
        coroutineScope.launch {
            var newsList = NewsApi.retrofitService.getNewsList()
            try {
                var listResult = newsList.await()
                _news.postValue(listResult)
            } catch (t: Throwable) {
                _news.postValue(ArrayList())
            }
        }
    }
}