package io.github.seoj17.canyongg.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatPerks(
    val defense: Int,
    val flex: Int,
    val offense: Int,
)
