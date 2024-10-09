package com.example.data.contract.datasource.newsSource

import com.example.domain.model.Source

interface NewsSourceOnlineDataSource {

    suspend fun getSources(category:String):List<Source>?

}