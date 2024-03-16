package com.example.news.News

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.api.newsResponse.ArticlesItem
import com.example.news.databinding.ItemNewsBinding

class NewsAdapter(var newsList:List<ArticlesItem?>?):Adapter<NewsAdapter.ViewHolder>() {



    class ViewHolder(val itemBinding: ItemNewsBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(news:ArticlesItem?){
            itemBinding.newsItem=news
            itemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding=ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = newsList?.size?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList?.get(position))
    }

    fun changeData(articles: List<ArticlesItem?>?) {
        newsList=articles
        notifyDataSetChanged()
    }
}