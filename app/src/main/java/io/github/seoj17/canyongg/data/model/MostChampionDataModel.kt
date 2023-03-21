package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.local.user.RegisterUserMostChampEntity

data class MostChampionDataModel(
    val champName: String,
    val puuid: String,
    val kda: Double,
    val winRate: Int,
) {
    companion object {
        operator fun invoke(entity: RegisterUserMostChampEntity): MostChampionDataModel {
            return MostChampionDataModel(
                champName = entity.champName,
                puuid = entity.userPuuid,
                kda = entity.champKda,
                winRate = entity.champWinRate,
            )
        }

        operator fun invoke(entities: List<RegisterUserMostChampEntity>): List<MostChampionDataModel> {
            return entities.map {
                invoke(it)
            }
        }
    }
}
