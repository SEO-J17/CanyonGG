package io.github.seoj17.canyongg.data.remote.response.perks

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PerksResponse(
    @Json(name = "icon")
    val icon: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "slots")
    val slots: List<Slot>,
)
