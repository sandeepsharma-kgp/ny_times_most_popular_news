package com.example.nyt_mostpopular

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class NewsModel (

    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("num_results") val num_results : Int,
    @SerializedName("results") val results : List<Results>
)

@Parcelize
data class Media (

    @SerializedName("type") val type : String?,
    @SerializedName("subtype") val subtype : String?,
    @SerializedName("caption") val caption : String?,
    @SerializedName("copyright") val copyright : String?,
    @SerializedName("approved_for_syndication") val approved_for_syndication : Int,
    @Json(name = "media-metadata") val mediaMetadata : List<MediaMetadata>
) : Parcelable

@Parcelize
data class MediaMetadata (

@SerializedName("url") val url : String,
@SerializedName("format") val format : String,
@SerializedName("height") val height : Int,
@SerializedName("width") val width : Int
) : Parcelable

@Parcelize
data class Results (

    @SerializedName("url") val url : String,
    @SerializedName("adx_keywords") val adx_keywordswords : String?,
    @SerializedName("column") val column : String?,
    @SerializedName("section") val section : String,
    @SerializedName("byline") val byline : String,
    @SerializedName("type") val type : String,
    @SerializedName("title") val title : String,
    @SerializedName("abstract") val abstract : String,
    @SerializedName("published_date") val published_date : String,
    @SerializedName("source") val source : String,
    @SerializedName("id") val id : String,
    @SerializedName("asset_id") val asset_id : String,
    @SerializedName("views") val views : Int,
//    @SerializedName("des_facet") val des_facet : String?,
//    @SerializedName("org_facet") val org_facet : String?,
//    @SerializedName("per_facet") val per_facet : String?,
//    @SerializedName("geo_facet") val geo_facet : String?,
    @SerializedName("media") val media : List<Media>
) : Parcelable