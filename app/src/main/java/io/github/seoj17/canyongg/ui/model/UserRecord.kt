package io.github.seoj17.canyongg.ui.model

data class UserRecord(
    val wholeMatch: Int = 0,
    val winCount: Int = 0,
    val loseCount: Int = 0,
    val winRate: Int = 0,
    val kda: Double = 0.0,
    val largestKill: Int = 0
)