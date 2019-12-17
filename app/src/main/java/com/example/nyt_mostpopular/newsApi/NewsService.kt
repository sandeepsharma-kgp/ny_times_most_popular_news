package com.example.nyt_mostpopular.newsApi

import com.example.nyt_mostpopular.newsModel.NewsModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.nytimes.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

//
//private val retrofit = Retrofit.Builder()
//    .baseUrl(BASE_URL)
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()

interface NewsService {

   @GET("svc/mostpopular/v2/mostviewed/all-sections/{past-day}.json")
    fun getNewsList(@Path("past-day")pastDay: String, @Query("api-key")type: String) : Deferred<NewsModel>
}

object NewsApi {
    val retrofitService : NewsService by lazy {
        retrofit.create(NewsService::class.java)
    }
}