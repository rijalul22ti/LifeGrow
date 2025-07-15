package com.example.tanpakelaparan.basic_api.data.model

data class MakananResponse(
    val cursor: String,
    val items: List<MakananItems>,
    val next_page: String,
)

data class MakananItems(
    val _created: Double,
    val _data_type: String,
    val _is_deleted: Boolean,
    val _modified: Double,
    val _self_link: String,
    val _user: String,
    val _uuid: String,
    val description: String,
    val name: String,
    val imageUrl: String,
    val price: Int,
    val nutrition: String
)
