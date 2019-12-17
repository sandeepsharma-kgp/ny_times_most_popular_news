package com.example.nyt_mostpopular

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nyt_mostpopular.newsListView.NewsAdapter
import com.example.nyt_mostpopular.newsListView.NewsApiStatus
import com.example.nyt_mostpopular.newsModel.Results

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Results>?) {
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgSrcUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        var imgUrl = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("newsApiStatus")
fun bindStatus(statusImageView : ImageView, status : NewsApiStatus?) {
    when(status) {
        NewsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_dashboard_black_24dp)
        }
        NewsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        NewsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}