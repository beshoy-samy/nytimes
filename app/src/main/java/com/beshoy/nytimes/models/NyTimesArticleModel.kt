package com.beshoy.nytimes.models

data class NyTimesArticleModel(
    val articleUrl: String,
    val id: Long,
    val source: String,
    val publishedDate: String,
    val updatedDate: String,
    val section: String,
    val keywords: List<String>,
    val byline: String,
    val type: String,
    val title: String,
    val abstract: String,
    val mediaModels: List<ArticleMediaModel>
)
