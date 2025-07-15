package com.example.tanpakelaparan.basic_api.data.model

data class MakananPostRequest(
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val nutrition: String
)

