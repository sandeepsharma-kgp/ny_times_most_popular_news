package com.example.nyt_mostpopular.detailedNews

import android.app.Application
import androidx.lifecycle.*
import com.example.nyt_mostpopular.newsModel.Results


/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(@Suppress("UNUSED_PARAMETER")results: Results, app: Application) : AndroidViewModel(app) {
    fun getTitle(): String? {
        return selectedNews.value?.title
    }

    private val _selectedNews = MutableLiveData<Results>()
    val selectedNews: LiveData<Results>
        get() = _selectedNews

    init {
        _selectedNews.value = results
    }
}
