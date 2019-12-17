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
import com.example.nyt_mostpopular.databinding.FragmentPastWeekBinding

class PastWeekNewsListView : Fragment() {

    private lateinit var pastWeekViewModel: NewsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPastWeekBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val application = requireNotNull(activity).application
        val viewModelFactory =
            NewsListViewModelFactory("7", application)
        pastWeekViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)
        binding.viewModel = pastWeekViewModel
        binding.newsList.adapter =
            NewsAdapter(NewsAdapter.OnClickListener {
                pastWeekViewModel.displayNewsDetails(it)
            })
        pastWeekViewModel.navigateToSelectedNews.observe(this, Observer {
            if(null != it) {
                this.findNavController().navigate(PastWeekNewsListViewDirections.actionShowDetail(it))
                pastWeekViewModel.displayNewsDetailsComplete()
            }
        })
        return binding.root
    }
}