package com.beshoy.nytimes.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleMediaModel(
    val type: String,
    val caption: String,
    val copyright: String,
    val url: String,
) : Parcelable