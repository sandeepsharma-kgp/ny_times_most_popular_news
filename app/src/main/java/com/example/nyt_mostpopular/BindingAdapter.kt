package com.example.nyt_mostpopular

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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
//            .apply(
//                RequestOptions()
//                    .placeholder(R.drawable.loading_animation)
//                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

