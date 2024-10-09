package com.example.news.News

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.ViewMessage
import com.example.news.api.ApiManager
import com.example.news.api.newsResponse.ArticlesItem
import com.example.news.api.sourceResponse.Source
import com.example.news.api.sourceResponse.SourcesResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel:ViewModel() {

    var isVisibleLiveData=MutableLiveData<Boolean>()
    var newsLiveData = MutableLiveData<List<ArticlesItem?>?>()
    var message = MutableLiveData<ViewMessage>()


    fun loadNews(source:Source){

        isVisibleLiveData.value=true

        source.id?.let {sourceId->
            viewModelScope.launch {
                try{
                    val response=  ApiManager.getService().getNews(sources = sourceId)
                    newsLiveData.postValue(response.articles)
                }
                catch (e: HttpException) {
                    val responseJson = e.response()?.errorBody()?.string()
                    val errorResponse =
                        Gson().fromJson(responseJson, SourcesResponse::class.java)
                    message.postValue ((ViewMessage(
                        message = errorResponse.message ?: "Something Went wrong",
                        posActionName = "Try Again",
                        posAction = { loadNews(source) }
                    )))
                } catch (e: Exception) {
                    message.postValue((ViewMessage(
                        message = e.message ?: "Something Went wrong")))
                }
                finally {
                    isVisibleLiveData.postValue(false)

                }

            }

        }
    }
}