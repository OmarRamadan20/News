package com.example.news.News

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.contract.repository.newsRepo.NewsRepository
import com.example.news.ViewMessage
import com.example.domain.model.ArticleItem
import com.example.domain.model.Source
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository):ViewModel() {

    var isVisibleLiveData=MutableLiveData<Boolean>()
    var newsLiveData = MutableLiveData<List<ArticleItem?>?>()
    var message = MutableLiveData<ViewMessage>()


    fun loadNews(source: Source){

        isVisibleLiveData.value=true

        source.id?.let {sourceId->
            viewModelScope.launch {
                try{
                    val response=  newsRepository.getNews(sourceId = sourceId)
                    newsLiveData.postValue(response)
                }
                catch (e: HttpException) {
                    val responseJson = e.response()?.errorBody()?.string()
                    val errorResponse =
                        Gson().fromJson(responseJson, com.example.data.api.sourceResponse.SourcesResponse::class.java)
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