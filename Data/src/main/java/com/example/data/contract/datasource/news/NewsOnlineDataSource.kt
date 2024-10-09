package com.example.data.contract.datasource.news

import com.example.domain.model.ArticleItem

interface NewsOnlineDataSource {

     suspend fun getNews(sourceId:String):List<ArticleItem>?
}