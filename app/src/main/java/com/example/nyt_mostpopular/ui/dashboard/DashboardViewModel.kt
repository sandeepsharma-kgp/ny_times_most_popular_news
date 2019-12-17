package com.example.nyt_mostpopular.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyt_mostpopular.NewsApi
import com.example.nyt_mostpopular.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

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
            var newsList = NewsApi.retrofitService.getNewsList("7","IJWn5aWd97vStEKZCPa80kGfT31jeu0b")
            try {
                var listResult = newsList.await()
                _news.value = listResult.results
                Log.e("RESULT", listResult.toString())
            } catch (t: Throwable) {
                _news.value = null
                Log.e("EROR", t.toString())
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