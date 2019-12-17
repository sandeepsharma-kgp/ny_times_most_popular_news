package com.example.nyt_mostpopular.detailedNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nyt_mostpopular.R
import com.example.nyt_mostpopular.databinding.FragmentDetailBinding

/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class DetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val results = DetailFragmentArgs.fromBundle(arguments!!).selectedNews
        val viewModelFactory =
            DetailViewModelFactory(results, application)
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        detailViewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)
        binding.viewModel = detailViewModel
        val mWebView = binding.root.findViewById(R.id.webView) as WebView
        mWebView.setWebViewClient(WebViewClient())
        detailViewModel.selectedNews.observe(this, Observer {
            it?.let {
                mWebView.loadUrl(it.url)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = detailViewModel.getTitle()
    }
}