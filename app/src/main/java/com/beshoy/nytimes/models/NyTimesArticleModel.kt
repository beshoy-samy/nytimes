package com.beshoy.nytimes.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable {
    val primaryIcon: String? = mediaModels.firstOrNull()?.url
}
