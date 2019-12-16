package com.example.nyt_mostpopular

import com.squareup.moshi.Json

data class NewsModel (
    val id: String,
    val byLine: String,
    val url: String,
    val caption: String,
    val date: String,
    @Json(name = "img_src") val imgUrl: String
)