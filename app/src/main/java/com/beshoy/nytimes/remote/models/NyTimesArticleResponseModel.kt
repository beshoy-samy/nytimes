package com.beshoy.nytimes.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NyTimesArticleResponseModel(
    @Json(name = "uri")
    val uri: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "asset_id")
    val assetId: Long? = null,
    @Json(name = "source")
    val source: String? = null,
    @Json(name = "published_date")
    val publishedDate: String? = null,
    @Json(name = "updated")
    val updated: String? = null,
    @Json(name = "section")
    val section: String? = null,
    @Json(name = "subsection")
    val subsection: String? = null,
    @Json(name = "nytdsection")
    val nytdsection: String? = null,
    @Json(name = "adx_keywords")
    val adxKeywords: String? = null, // separated by ;
    @Json(name = "column")
    val column: Any? = null,
    @Json(name = "byline")
    val byline: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "abstract")
    val `abstract`: String? = null,
    @Json(name = "des_facet")
    val desFacet: List<String>? = null,
    @Json(name = "org_facet")
    val orgFacet: List<String>? = null,
    @Json(name = "per_facet")
    val perFacet: List<Any>? = null,
    @Json(name = "geo_facet")
    val geoFacet: List<String>? = null,
    @Json(name = "media")
    val mediaResponseModels: List<MediaResponseModel>? = null,
    @Json(name = "eta_id")
    val etaId: Int? = null
)