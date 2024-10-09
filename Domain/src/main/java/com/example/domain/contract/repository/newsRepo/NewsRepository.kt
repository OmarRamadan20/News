package com.example.domain.contract.repository.newsRepo

import com.example.domain.model.ArticleItem
import com.example.domain.model.Source

interface NewsRepository {
    suspend fun getNews(sourceId:String):List<ArticleItem>?
}