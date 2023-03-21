package io.github.seoj17.canyongg.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    val bans: List<Any>,
    val objectives: Objectives,
    val teamId: Int,
    val win: Boolean,
)
