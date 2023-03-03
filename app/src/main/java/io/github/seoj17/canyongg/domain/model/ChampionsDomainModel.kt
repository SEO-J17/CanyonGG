package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.champions.ChampionsEntity

data class ChampionsDomainModel(
    val key: Int,
    val name: String,
) {
    companion object {
        fun toEntity(domain: ChampionsDomainModel): ChampionsEntity {
            return ChampionsEntity(
                key = domain.key,
                name = domain.name
            )
        }

        fun toEntity(domain: List<ChampionsDomainModel>): List<ChampionsEntity> {
            return domain.map {
                toEntity(it)
            }
        }
    }
}