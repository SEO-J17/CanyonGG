package io.github.seoj17.canyongg.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Champion(
    val first: Boolean,
    val kills: Int
)