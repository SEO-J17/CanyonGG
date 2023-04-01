package io.github.seoj17.domain.model

data class ChampInfoDomainModel(
    val name: String,
    val winRate: Int = 0,
    val kda: Double = 0.0,
)
