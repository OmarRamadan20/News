package com.example.data.dataSourceImpl.news

import com.example.data.api.WebServices
import com.example.data.contract.datasource.news.NewsOnlineDataSource
import com.example.domain.model.ArticleItem
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(val webServices: WebServices):NewsOnlineDataSource {

    override suspend fun getNews(sourceId: String): List<ArticleItem>? {
       return webServices.getNews(sourceId=sourceId).articles?.map {articledto->
           articledto!!.toArticle()
       }
    }
}