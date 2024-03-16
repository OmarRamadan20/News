package com.example.news.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.ViewMessage
import com.example.news.api.ApiManager
import com.example.news.api.newsResponse.ArticlesItem
import com.example.news.api.newsResponse.NewsResponse
import com.example.news.api.sourceResponse.Source
import com.example.news.api.sourceResponse.SourcesResponse
import com.example.news.databinding.FragmentNewsBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    lateinit var viewBinding: FragmentNewsBinding
    var viewModel = NewsViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    var source: Source? = null


    var adapter = NewsAdapter(null)
    private fun showNewsList(articles: List<ArticlesItem?>?) {
        adapter.changeData(articles)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerNews.adapter = adapter
        observeDataChanged()

    }

    private fun observeDataChanged() {
        viewModel.isVisibleLiveData.observe(viewLifecycleOwner) {
            changeLoadingVisibility(it)
        }
        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            showNewsList(it)
        }
        viewModel.message.observe(viewLifecycleOwner) {
            showError(it)
        }
    }

    private fun showError(message: ViewMessage?) {
        viewBinding.errorView.isVisible = true
        viewBinding.progressBar.isVisible = false
        viewBinding.errorMessage.text = message?.message
        viewBinding.errorButton.setOnClickListener({
            message?.posAction?.invoke()
        })

    }

    fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        viewBinding.progressBar.isVisible = isLoadingVisible
    }

    fun changeSource(source: Source) {
        this.source = source
        viewModel.loadNews(source)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }
}