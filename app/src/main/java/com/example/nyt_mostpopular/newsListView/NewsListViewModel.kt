package com.example.nyt_mostpopular.newsListView

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nyt_mostpopular.newsApi.NewsApi
import com.example.nyt_mostpopular.newsModel.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class NewsApiStatus {
    LOADING, ERROR, DONE
}

class NewsListViewModel(app: Application, val timePeriod: String) : AndroidViewModel(app) {
    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<NewsApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<NewsApiStatus>
        get() = _status
    private val _news = MutableLiveData<List<Results>>()
    val news: LiveData<List<Results>>
        get() = _news

    private val _navigateToSelectedNews= MutableLiveData<Results>()
    val navigateToSelectedNews : LiveData<Results>
        get() = _navigateToSelectedNews

    init {
        getNewsList()
    }

    private lateinit var viewModelJob : Job
    private fun getNewsList() {
        viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
            var newsList = NewsApi.retrofitService.getNewsList(timePeriod,"IJWn5aWd97vStEKZCPa80kGfT31jeu0b")
            try {
                _status.value = NewsApiStatus.LOADING
                var listResult = newsList.await()
                _news.value = listResult.results
                _status.value = NewsApiStatus.DONE
                Log.e("RESULT", listResult.toString())
            } catch (t: Throwable) {
                _news.value = null
                _status.value = NewsApiStatus.ERROR
                Log.e("ERROR", t.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayNewsDetails(result: Results) {
        _navigateToSelectedNews.value = result
    }

    fun displayNewsDetailsComplete() {
        _navigateToSelectedNews.value = null
    }
}