package com.example.tanpakelaparan.basic_api.data.model

// Data class utama yang mewakili respons JSON
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

// Data class untuk setiap artikel di dalam "articles"
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

// Data class untuk bagian "source"
data class Source(
    val id: String?,
    val name: String
)
