package com.example.nyt_mostpopular.newsListView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nyt_mostpopular.databinding.FragmentPastDayBinding

class PastDayNewsListView : Fragment() {

    private lateinit var pastDayViewModel: NewsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPastDayBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val application = requireNotNull(activity).application
        val viewModelFactory =
            NewsListViewModelFactory("1", application)
        pastDayViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)
        binding.viewModel = pastDayViewModel
        binding.newsList.adapter =
            NewsAdapter(NewsAdapter.OnClickListener {
                pastDayViewModel.displayNewsDetails(it)
            })
        pastDayViewModel.navigateToSelectedNews.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    PastDayNewsListViewDirections.actionShowDetail(it)
                )
                pastDayViewModel.displayNewsDetailsComplete()
            }
        })
        return binding.root
    }
}