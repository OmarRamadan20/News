package com.example.data.repositoryImpl.newsSources

import com.example.data.contract.datasource.newsSource.NewsSourceOnlineDataSource
import com.example.domain.contract.repository.newsSourceRepo.NewsSourceRepository
import com.example.domain.model.ArticleItem
import com.example.domain.model.Source
import javax.inject.Inject

class NewsSourcesRepositoryImpl @Inject constructor(val newsSourceOnlineDataSource: NewsSourceOnlineDataSource):NewsSourceRepository {
    override suspend fun getNewsSources(category: String): List<Source>? {
        return newsSourceOnlineDataSource.getSources(category=category)
    }
}