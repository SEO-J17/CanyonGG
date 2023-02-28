package io.github.seoj17.canyongg.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dragon(
    val first: Boolean,
    val kills: Int,
)