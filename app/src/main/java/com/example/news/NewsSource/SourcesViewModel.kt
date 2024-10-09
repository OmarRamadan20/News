package com.example.news.NewsSource

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.sourceResponse.SourcesResponse
import com.example.domain.contract.repository.newsSourceRepo.NewsSourceRepository
import com.example.news.ViewMessage
import com.example.domain.model.Source
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor
    (private val newsSourceRepository: NewsSourceRepository):ViewModel() {

    val isVisibleLiveData = MutableLiveData<Boolean>()
    val sourcesListLiveData = MutableLiveData<List<Source?>?>()
    val message = MutableLiveData<ViewMessage>()
    fun getNewsSources(categoryId: String) {
        isVisibleLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsSourceRepository.getNewsSources(category = categoryId)
                sourcesListLiveData.postValue(response)

            } catch (e: HttpException) {
                val responseJson = e.response()?.errorBody()?.string()
                val errorResponse =
                    Gson().fromJson(responseJson, SourcesResponse::class.java)
                message.postValue ((ViewMessage(
                    message = errorResponse.message ?: "Something Went wrong",
                    posActionName = "Try Again",
                    posAction = { getNewsSources(categoryId) }
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


