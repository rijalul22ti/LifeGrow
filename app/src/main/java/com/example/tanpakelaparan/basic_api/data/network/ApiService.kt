package com.example.tanpakelaparan.basic_api.data.network

import com.example.tanpakelaparan.basic_api.data.model.MakananPostRequest
import com.example.tanpakelaparan.basic_api.data.model.MakananResponse
import com.example.tanpakelaparan.basic_api.data.model.NewsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("v2/everything?q=tesla&from=2024-11-04&sortBy=publishedAt&apiKey=86c83e34c5bf43cca8f413b7097335c4\n")
    suspend fun getNews(): NewsResponse
    @POST("product")
    suspend fun createMakanan(
        @Header("Authorization") token: String,
        @Body products: List<MakananPostRequest>,
    ): MakananResponse

    @GET("product")
    suspend fun getMakanan(
        @Header("Authorization") token: String
    ): MakananResponse
}