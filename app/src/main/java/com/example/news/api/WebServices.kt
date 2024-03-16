package com.example.news.api

import com.example.news.api.newsResponse.NewsResponse
import com.example.news.api.sourceResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String =Constants.apiKey
    ): Call<SourcesResponse>


    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") apiKey: String =Constants.apiKey,
        @Query("sources")sources:String
    ):Call<NewsResponse>

}