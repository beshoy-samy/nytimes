package com.beshoy.nytimes.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponseModel<T>(
    val status: String? = null,
    val num_results: Int? = null,
    val copyright: String? = null,
    val results: T? = null,
)