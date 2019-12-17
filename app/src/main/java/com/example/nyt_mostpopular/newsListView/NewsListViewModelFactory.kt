package com.example.nyt_mostpopular.newsListView

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsListViewModelFactory(
    private val timePeriod: String,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            return NewsListViewModel(
                application,
                timePeriod
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}