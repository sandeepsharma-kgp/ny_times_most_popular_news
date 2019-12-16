package com.example.nyt_mostpopular

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<NewsModel>?) {
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(data)
}