package com.example.nyt_mostpopular.ui.home

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

class HomeViewModel : ViewModel() {

    private val _news = MutableLiveData<List<Results>>()
    val news: LiveData<List<Results>>
        get() = _news

    init {
        getNewsList()
    }

    private lateinit var viewModelJob : Job
    private fun getNewsList() {
        viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
            var newsList = NewsApi.retrofitService.getNewsList("IJWn5aWd97vStEKZCPa80kGfT31jeu0b")
            try {
                var listResult = newsList.await()
                _news.value = listResult.results
                Log.e("RESULT", newsList.toString())
            } catch (t: Throwable) {
                _news.value = ArrayList()
                Log.e("EROR", t.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}