package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.news.NewsSource.NewsSourceFragment
import com.example.news.api.sourceResponse.SourcesResponse
import com.example.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,NewsSourceFragment())
            .commit()
    }
}