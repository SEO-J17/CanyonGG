package io.github.seoj17.canyongg.data.remote.response.champion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChampResponse(
    @Json(name = "data")
    val data: Map<String, ChampData>,
    @Json(name = "format")
    val format: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "version")
    val version: String,
)