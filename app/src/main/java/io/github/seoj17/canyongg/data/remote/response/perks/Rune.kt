package io.github.seoj17.canyongg.data.remote.response.perks

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rune(
    val icon: String,
    val id: Int,
    val key: String,
    val longDesc: String,
    val name: String,
    val shortDesc: String,
)
