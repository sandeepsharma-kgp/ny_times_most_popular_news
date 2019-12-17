package com.example.nyt_mostpopular.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nyt_mostpopular.DetailFragment
import com.example.nyt_mostpopular.DetailViewModelFactory
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
        val application = requireNotNull(activity).application
        val viewModelFactory = NewsListViewModelFactory("1", application)
        homeViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
        binding.newsList.adapter = NewsAdapter(NewsAdapter.OnClickListener{
            homeViewModel.displayNewsDetails(it)
        })
        homeViewModel.navigateToSelectedNews.observe(this, Observer {
            if(null != it) {
                this.findNavController().navigate(HomeFragmentDirections.actionShowDetail(it))
                homeViewModel.displayNewsDetailsComplete()
            }
        })
        return binding.root
    }
}