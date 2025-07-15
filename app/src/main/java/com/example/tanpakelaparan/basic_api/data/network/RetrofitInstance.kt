package com.example.tanpakelaparan.basic_api.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL_NEWSAPI = "https://newsapi.org"
    private const val BASE_URL_CRUDAPI = "https://crudapi.co.uk/api/v1/"

    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun getNewsApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_NEWSAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    fun getCrudApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_CRUDAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}