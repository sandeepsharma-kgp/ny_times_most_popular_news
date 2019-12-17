package com.example.nyt_mostpopular.newsListView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nyt_mostpopular.newsModel.Results
import com.example.nyt_mostpopular.databinding.NewsItemBinding

class NewsAdapter(private val onClickListener: OnClickListener) : ListAdapter<Results, NewsAdapter.NewsViewHolder>(
    DiffCallback
) {
    companion object DiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class NewsViewHolder(private var binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: Results) {
            binding.result = newsItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(newsItem)
        }
        holder.bind(newsItem)
    }

    class OnClickListener(val clickListener: (newsItem: Results) -> Unit) {
        fun onClick(newsItem: Results) = clickListener(newsItem)
    }

}