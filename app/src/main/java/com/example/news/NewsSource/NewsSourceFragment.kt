package com.example.news.NewsSource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.News.NewsFragment
import com.example.news.R
import com.example.news.ViewMessage
import com.example.news.api.sourceResponse.Source
import com.example.news.databinding.FragmentNewsSourcesBinding
import com.google.android.material.tabs.TabLayout

class NewsSourceFragment(private val cateogryId:String) : Fragment() {
    private lateinit var binding: FragmentNewsSourcesBinding
    private lateinit var viewModel: SourcesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SourcesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsSourcesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()
        viewModel.getNewsSources(cateogryId)
    }


    private fun observeLiveData() {
        viewModel.isVisibleLiveData.observe(viewLifecycleOwner) {
            changeLoadingVisibility(it)
        }
        viewModel.message.observe(viewLifecycleOwner) {
            showError(it)
        }
        viewModel.sourcesListLiveData.observe(viewLifecycleOwner) {
            showNewSources(it)
        }

    }

    val newsFragment = NewsFragment()

    private fun initViews() {

        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, newsFragment)
            .commit()

        binding.errorButton.setOnClickListener {
            binding.errorView.isVisible = true
            viewModel.getNewsSources(cateogryId)
        }
    }

    private fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        binding.progressBar.isVisible = isLoadingVisible
    }

    private fun showNewSources(sources: List<Source?>?) {
        binding.errorView.isVisible = false
        binding.progressBar.isVisible = false
        sources?.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            binding.tabLayout.addTab(tab)
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                newsFragment.changeSource(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                newsFragment.changeSource(source)
            }
        })
        binding.tabLayout.getTabAt(0)?.select()
    }

    private fun showError(message: ViewMessage?) {
        binding.errorView.isVisible = true
        binding.errorMessage.text = message?.message
        binding.errorButton.text = message?.posActionName
        binding.errorButton.setOnClickListener{
            message?.posAction?.invoke()
        }

    }

}