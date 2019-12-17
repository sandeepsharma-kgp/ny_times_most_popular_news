package com.example.nyt_mostpopular.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.nyt_mostpopular.NewsAdapter
import com.example.nyt_mostpopular.R
import com.example.nyt_mostpopular.databinding.FragmentHomeBinding
import com.example.nyt_mostpopular.databinding.FragmentNotificationsBinding
import com.example.nyt_mostpopular.ui.home.HomeFragmentDirections
import com.example.nyt_mostpopular.ui.home.HomeViewModel
import com.example.nyt_mostpopular.ui.home.NewsListViewModelFactory

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNotificationsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val application = requireNotNull(activity).application
        val viewModelFactory = NewsListViewModelFactory("1", application)
        notificationsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.viewModel = notificationsViewModel
        binding.newsList.adapter = NewsAdapter(NewsAdapter.OnClickListener{
            notificationsViewModel.displayNewsDetails(it)
        })
        notificationsViewModel.navigateToSelectedNews.observe(this, Observer {
            if(null != it) {
                this.findNavController().navigate(HomeFragmentDirections.actionShowDetail(it))
                notificationsViewModel.displayNewsDetailsComplete()
            }
        })
        return binding.root
    }
}