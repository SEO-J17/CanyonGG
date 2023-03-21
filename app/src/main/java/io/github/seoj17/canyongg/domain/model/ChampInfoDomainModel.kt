package io.github.seoj17.canyongg.domain.model

data class ChampInfoDomainModel(
    val name: String,
    val winRate: Int = 0,
    val kda: Double = 0.0,
)
