package com.example.tanpakelaparan.basic_api.data.repository

import com.example.tanpakelaparan.basic_api.data.model.MakananPostRequest
import com.example.tanpakelaparan.basic_api.data.model.MakananResponse
import com.example.tanpakelaparan.basic_api.data.network.ApiService

class MakananRepository(private val api: ApiService) {
    private val tokenBearer = "Bearer SdXPiKMjinrAJAgd3PZcEBgyvLwQR04ff83Jd2AYdnN_WpzWrw"

    suspend fun fetchProduct(): MakananResponse {
        return api.getMakanan(tokenBearer)
    }

    suspend fun createProduct(products: List<MakananPostRequest>): MakananResponse {
        return api.createMakanan(tokenBearer, products)
    }
}