package com.example.news.api

import com.example.news.api.newsResponse.NewsResponse
import com.example.news.api.sourceResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String =Constants.apiKey,
        @Query("category") category:String
    ): SourcesResponse


    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String =Constants.apiKey,
        @Query("sources")sources:String
    ): NewsResponse

}