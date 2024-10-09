package com.example.data.dataSourceImpl.newsSource

import com.example.data.api.WebServices
import com.example.data.contract.datasource.newsSource.NewsSourceOnlineDataSource
import com.example.domain.model.Source
import javax.inject.Inject

class NewsSourceDataSourceImpl @Inject constructor(val webServices: WebServices):NewsSourceOnlineDataSource {


    override suspend fun getSources(category: String): List<Source>? {

        return webServices.getNewsSources(category=category).sourceDtos?.map {sourcedto->
            sourcedto!!.toSource()
        }
    }
}