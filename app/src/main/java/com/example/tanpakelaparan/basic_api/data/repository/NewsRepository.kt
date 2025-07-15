package com.example.tanpakelaparan.basic_api.data.repository

import com.example.tanpakelaparan.basic_api.data.model.NewsResponse
import com.example.tanpakelaparan.basic_api.data.network.ApiService

class NewsRepository (private val api: ApiService) {
    suspend fun fetchNews(): NewsResponse {
        return api.getNews()
    }
}