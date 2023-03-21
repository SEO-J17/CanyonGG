package io.github.seoj17.canyongg.data.remote.response.champion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RotationChampResponse(
    @Json(name = "freeChampionIds")
    val freeChampionIds: List<Int>,
)
