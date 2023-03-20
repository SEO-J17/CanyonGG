package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.user.RegisterUserMostChampEntity
import io.github.seoj17.canyongg.data.model.MostChampionDataModel

data class MostChampsDomainModel(
    val champName: String,
    val userPuuid: String,
    val champKda: Double,
    val champWinRate: Int,
) {
    companion object {
        operator fun invoke(data: MostChampionDataModel): MostChampsDomainModel {
            return MostChampsDomainModel(
                champName = data.champName,
                userPuuid = data.puuid,
                champKda = data.kda,
                champWinRate = data.winRate,
            )
        }

        operator fun invoke(entities: List<MostChampionDataModel>): List<MostChampsDomainModel> {
            return entities.map {
                invoke(it)
            }
        }

        fun toEntity(domain: MostChampsDomainModel): RegisterUserMostChampEntity {
            return RegisterUserMostChampEntity(
                champName = domain.champName,
                userPuuid = domain.userPuuid,
                champKda = domain.champKda,
                champWinRate = domain.champWinRate,
            )
        }

        fun toEntity(domain: List<MostChampsDomainModel>): List<RegisterUserMostChampEntity> {
            return domain.map {
                toEntity(it)
            }
        }
    }
}
