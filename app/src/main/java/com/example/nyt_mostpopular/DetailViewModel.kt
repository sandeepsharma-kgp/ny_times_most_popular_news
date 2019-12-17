/*
 *  Copyright 2018, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.nyt_mostpopular

import android.app.Application
import androidx.lifecycle.*


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
