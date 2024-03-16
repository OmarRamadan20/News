package com.example.news.NewsSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.ViewMessage
import com.example.news.api.ApiManager
import com.example.news.api.sourceResponse.Source
import com.example.news.api.sourceResponse.SourcesResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SourcesViewModel:ViewModel() {

    val isVisibleLiveData = MutableLiveData<Boolean>()
    val sourcesListLiveData = MutableLiveData<List<Source?>?>()
    val message = MutableLiveData<ViewMessage>()
     fun getNewsSources() {
        isVisibleLiveData.value=true
        ApiManager.getService()
            .getNewsSources()
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    isVisibleLiveData.value=false
                    if (response.isSuccessful) {
                        sourcesListLiveData.value=response.body()?.sources
                    } else {
                        val responseJson = response.errorBody()?.string()
                        val errorResponse =
                            Gson().fromJson(responseJson, SourcesResponse::class.java)
                        message.value=(ViewMessage(
                            message = errorResponse.message?:"Something Went wrong",
                            posActionName = "Try Again",
                            posAction = {getNewsSources()}
                        ))                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    isVisibleLiveData.value=true
                    message.value=(ViewMessage(
                        message =t.message?:"Something Went wrong",
                        posActionName = "Try Again",
                        posAction = {getNewsSources()}
                    ))

                }

            })
    }

}