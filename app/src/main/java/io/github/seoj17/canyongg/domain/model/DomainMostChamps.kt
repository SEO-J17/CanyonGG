package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.MyMostChampEntity

data class DomainMostChamps(
    val champName: String,
    val userPuuid: String,
    val champKda: Double,
    val champWinRate: Int,
) {
    companion object {
        operator fun invoke(entity: MyMostChampEntity): DomainMostChamps {
            return DomainMostChamps(
                champName = entity.champName,
                userPuuid = entity.userPuuid,
                champKda = entity.champKda,
                champWinRate = entity.champWinRate,
            )
        }

        operator fun invoke(entities: List<MyMostChampEntity>): List<DomainMostChamps> {
            return entities.map {
                invoke(it)
            }
        }
    }
}