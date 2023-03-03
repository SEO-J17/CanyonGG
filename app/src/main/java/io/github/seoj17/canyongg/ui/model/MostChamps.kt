package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.MostChampsDomainModel

data class MostChamps(
    val champName: String,
    val userPuuid: String,
    val champKda: Double,
    val champWinRate: Int,
) {
    companion object {
        operator fun invoke(domain: MostChampsDomainModel): MostChamps {
            return MostChamps(
                champName = domain.champName,
                userPuuid = domain.userPuuid,
                champKda = domain.champKda,
                champWinRate = domain.champWinRate,
            )
        }

        operator fun invoke(champs: List<MostChampsDomainModel>): List<MostChamps> {
            return champs.map {
                invoke(it)
            }
        }

        fun toDomainModel(summoner: Summoner, champInfo: ChampInfo): MostChampsDomainModel {
            return MostChampsDomainModel(
                champName = champInfo.name,
                userPuuid = summoner.puuid,
                champKda = champInfo.kda,
                champWinRate = champInfo.winRate,
            )
        }
    }
}
