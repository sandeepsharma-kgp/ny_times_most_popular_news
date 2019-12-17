package com.example.nyt_mostpopular.newsListView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nyt_mostpopular.databinding.FragmentPastMonthBinding

class PastMonthNewsListView : Fragment() {

    private lateinit var pastMonthViewModel: NewsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPastMonthBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val application = requireNotNull(activity).application
        val viewModelFactory =
            NewsListViewModelFactory("30", application)
        pastMonthViewModel =
            ViewModelProviders.of(this,viewModelFactory).get(NewsListViewModel::class.java)
        binding.viewModel = pastMonthViewModel
        binding.newsList.adapter =
            NewsAdapter(NewsAdapter.OnClickListener {
                pastMonthViewModel.displayNewsDetails(it)
            })
        pastMonthViewModel.navigateToSelectedNews.observe(this, Observer {
            if(null != it) {
                this.findNavController().navigate(PastMonthNewsListViewDirections.actionShowDetail(it))
                pastMonthViewModel.displayNewsDetailsComplete()
            }
        })
        return binding.root
    }
}