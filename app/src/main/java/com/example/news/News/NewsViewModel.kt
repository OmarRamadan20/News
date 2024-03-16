package com.example.news.News

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.ViewMessage
import com.example.news.api.ApiManager
import com.example.news.api.newsResponse.ArticlesItem
import com.example.news.api.newsResponse.NewsResponse
import com.example.news.api.sourceResponse.Source
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {

    var isVisibleLiveData=MutableLiveData<Boolean>()
    var newsLiveData = MutableLiveData<List<ArticlesItem?>?>()
    var message = MutableLiveData<ViewMessage>()


    fun loadNews(source:Source?){

        isVisibleLiveData.value=true

        source?.id?.let {sourceId->
            ApiManager.getService().getNews(sources = sourceId)
                .enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        isVisibleLiveData.value=false
                        if(response.isSuccessful){
                            newsLiveData.value= response.body()?.articles
                            return
                        }
                        else {
                            val responseJson = response.errorBody()?.string()
                            val errorResponse =
                                Gson().fromJson(responseJson, NewsResponse::class.java)
                            message.value=ViewMessage(
                                errorResponse.message?:"Something Went Wrong",
                                posAction = {loadNews(source)})

                        }
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        isVisibleLiveData.value= true
                        message.value=ViewMessage(message=t.message?:"Something Went wrong",
                            posActionName = "Try Again",
                            posAction = {loadNews(source)
                            Log.e("error","error")
                            }

                        )
        }
    })
        }
    }
}