package io.github.seoj17.canyongg.network.response

data class Team(
    val bans: List<Any>,
    val objectives: Objectives,
    val teamId: Int,
    val win: Boolean
)