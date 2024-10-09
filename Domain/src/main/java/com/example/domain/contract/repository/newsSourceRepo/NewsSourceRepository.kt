package com.example.domain.contract.repository.newsSourceRepo

import com.example.domain.model.ArticleItem
import com.example.domain.model.Source

interface NewsSourceRepository {
    suspend fun getNewsSources(category:String):List<Source>?
}