package io.github.seoj17.canyongg.data.remote.response.champion

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChampInfo(
    val attack: Int,
    val defense: Int,
    val difficulty: Int,
    val magic: Int,
)
