package io.github.seoj17.canyongg.data.remote.response.spell

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SpellResponse(
    @Json(name = "data")
    val data: Map<String, SpellInfo>,
    @Json(name = "type")
    val type: String,
    @Json(name = "version")
    val version: String,
) {
}