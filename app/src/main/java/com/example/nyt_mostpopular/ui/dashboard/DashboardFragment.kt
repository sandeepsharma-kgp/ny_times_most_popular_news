package com.example.nyt_mostpopular.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nyt_mostpopular.NewsAdapter
import com.example.nyt_mostpopular.R
import com.example.nyt_mostpopular.databinding.FragmentDashboardBinding
import com.example.nyt_mostpopular.databinding.FragmentHomeBinding
import com.example.nyt_mostpopular.ui.home.HomeFragmentDirections
import com.example.nyt_mostpopular.ui.home.HomeViewModel

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        binding.viewModel = dashboardViewModel
        binding.newsList.adapter = NewsAdapter(NewsAdapter.OnClickListener{
            dashboardViewModel.displayNewsDetails(it)
        })
        dashboardViewModel.navigateToSelectedNews.observe(this, Observer {
            if(null != it) {
                this.findNavController().navigate(HomeFragmentDirections.actionShowDetail(it))
                dashboardViewModel.displayNewsDetailsComplete()
            }
        })
        return binding.root
    }
}