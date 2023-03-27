package io.github.seoj17.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Baron(
    val first: Boolean,
    val kills: Int,
)
