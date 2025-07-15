package com.example.tanpakelaparan.basic_api.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odiproject.basic_api.utils.NetworkUtils
import com.example.odiproject.basic_api.utils.Resource
import com.example.tanpakelaparan.basic_api.data.model.MakananPostRequest
import com.example.tanpakelaparan.basic_api.data.model.MakananResponse
import com.example.tanpakelaparan.basic_api.data.repository.MakananRepository
import kotlinx.coroutines.launch

class MakananViewModel(private val repository: MakananRepository) : ViewModel() {
    private val _data = MutableLiveData<Resource<MakananResponse>>()
    val data: LiveData<Resource<MakananResponse>> = _data

    private val _createStatus = MutableLiveData<Resource<Unit>>()
    val createStatus: LiveData<Resource<Unit>> = _createStatus

    fun getMakanan(context: Context, forceRefresh: Boolean = false) {
        if (_data.value == null || forceRefresh) {
            if (NetworkUtils.isNetworkAvailable(context)) {
                viewModelScope.launch {
                    try {
                        _data.value = Resource.Loading()
                        val response = repository.fetchProduct()
                        if (response.items.isEmpty()) {
                            _data.postValue(Resource.Empty("No data found"))
                        } else {
                            _data.postValue(Resource.Success(response))
                        }
                    } catch (e: Exception) {
                        _data.postValue(Resource.Error("Unknown error: ${e.message}"))
                    }
                }
            } else {
                _data.postValue(Resource.Error("No Internet Connection"))
            }
        }
    }

    fun createMakanan(context: Context, product: List<MakananPostRequest>) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            viewModelScope.launch {
                try {
                    _createStatus.value = Resource.Loading()

                    val response = repository.createProduct(product)
                    _createStatus.postValue(Resource.Success(Unit))

                    getMakanan(context, forceRefresh = true)
                } catch (e: Exception) {
                    _createStatus.postValue(Resource.Error("Unknown error: ${e.message}"))
                }
            }
        } else {
            _createStatus.postValue(Resource.Error("No Internet Connection"))
        }

    }
}