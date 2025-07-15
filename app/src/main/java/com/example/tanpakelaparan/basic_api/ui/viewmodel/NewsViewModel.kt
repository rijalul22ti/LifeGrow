package com.example.tanpakelaparan.basic_api.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odiproject.basic_api.utils.NetworkUtils
import com.example.odiproject.basic_api.utils.Resource
import com.example.tanpakelaparan.basic_api.data.model.NewsResponse
import com.example.tanpakelaparan.basic_api.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel (private val repository: NewsRepository): ViewModel() {
    private val _data = MutableLiveData<Resource<NewsResponse>>()
    val data: LiveData<Resource<NewsResponse>> = _data

    fun getNews(context: Context, forceRefresh: Boolean = false){
        if(_data.value == null || forceRefresh){
            if(NetworkUtils.isNetworkAvailable(context)){
                viewModelScope.launch {
                    try {
                        _data.value = Resource.Loading()
                        val response = repository.fetchNews()
                        if(response.articles.isEmpty()){
                            _data.postValue(Resource.Empty("No data found"))
                        }else{
                            _data.postValue(Resource.Success(response))
                        }
                    }catch (e: Exception){
                        _data.postValue(Resource.Error("Unknown error: ${e.message}"))
                    }
                }
            }else{
                _data.postValue(Resource.Error("No Internet Connection"))
            }
        }
//        try {
//            val response = repository.fetchNews()
//            emit(response.articles)
//        } catch ( e: Exception ){
//            emit(null)
//        }
    }
}