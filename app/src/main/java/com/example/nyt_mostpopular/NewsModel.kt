package com.example.nyt_mostpopular

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsModel (
    val id: String,
    val byLine: String,
    val url: String,
    val caption: String,
    val date: String,
    @Json(name = "img_src") val imgUrl: String
) : Parcelable