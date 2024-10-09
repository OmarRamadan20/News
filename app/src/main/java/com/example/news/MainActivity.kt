package com.example.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.news.NewsSource.NewsSourceFragment
import com.example.news.cateogries.CategoryFragment
import com.example.news.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewBinding:ActivityMainBinding
    lateinit var categoriesFragment: CategoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initFragments()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,categoriesFragment)
            .commit()
    }
    private fun initFragments() {
        categoriesFragment= CategoryFragment {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, NewsSourceFragment(it.id))
                .commit()
        }
    }
}