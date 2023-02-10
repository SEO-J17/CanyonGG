package io.github.seoj17.canyongg.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Selection(
    val perk: Int,
    val var1: Int,
    val var2: Int,
    val var3: Int
)