package io.github.seoj17.canyongg.domain.model

data class UserRecordDomainModel(
    val wholeMatch: Int = 0,
    val winCount: Int = 0,
    val loseCount: Int = 0,
    val winRate: Int = 0,
    val kda: Double = 0.0,
    val largestKill: Int = 0,
)