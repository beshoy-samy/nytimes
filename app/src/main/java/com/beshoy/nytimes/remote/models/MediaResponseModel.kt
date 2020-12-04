package com.beshoy.nytimes.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaResponseModel(
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "subtype")
    val subtype: String? = null,
    @Json(name = "caption")
    val caption: String? = null,
    @Json(name = "copyright")
    val copyright: String? = null,
    @Json(name = "approved_for_syndication")
    val approvedForSyndication: Int? = null,
    @Json(name = "media-metadata")
    val mediaMetadataResponseModels: List<MediaMetadataResponseModel>? = null
)