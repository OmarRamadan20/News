package com.example.data.repositoryImpl.news

import com.example.data.contract.datasource.news.NewsOnlineDataSource
import com.example.domain.contract.repository.newsRepo.NewsRepository
import com.example.domain.model.ArticleItem
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (val newsOnlineDataSource: NewsOnlineDataSource):NewsRepository {

    override suspend fun getNews(sourceId: String): List<ArticleItem>? {
       return newsOnlineDataSource.getNews(sourceId)
    }
}