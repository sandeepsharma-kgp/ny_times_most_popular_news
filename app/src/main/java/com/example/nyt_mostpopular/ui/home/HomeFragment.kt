package com.example.nyt_mostpopular.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nyt_mostpopular.NewsAdapter
import com.example.nyt_mostpopular.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
        binding.newsList.adapter = NewsAdapter(NewsAdapter.OnClickListener{

        })
        homeViewModel.news.observe(this, Observer {

        })
        return binding.root
    }
}