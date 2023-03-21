package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.champions.ChampionsEntity
import io.github.seoj17.canyongg.data.model.ChampionsDataModel

data class ChampionsDomainModel(
    val key: Int,
    val name: String,
) {
    companion object {
        operator fun invoke(data: ChampionsDataModel): ChampionsDomainModel {
            return ChampionsDomainModel(
                key = data.key,
                name = data.name,
            )
        }

        fun toEntity(domain: ChampionsDomainModel): ChampionsEntity {
            return ChampionsEntity(
                key = domain.key,
                name = domain.name,
            )
        }

        fun toEntity(domain: List<ChampionsDomainModel>): List<ChampionsEntity> {
            return domain.map {
                toEntity(it)
            }
        }
    }
}
