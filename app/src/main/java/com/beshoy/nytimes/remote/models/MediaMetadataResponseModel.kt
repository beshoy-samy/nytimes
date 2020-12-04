package com.beshoy.nytimes.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaMetadataResponseModel(
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "format")
    val format: String? = null,
    @Json(name = "height")
    val height: Int? = null,
    @Json(name = "width")
    val width: Int? = null
)