package io.github.seoj17.presentaion.model

import io.github.seoj17.domain.model.ChampInfoDomainModel

data class ChampInfo(
    val name: String,
    val winRate: Int,
    val kda: Double,
) {
    companion object {
        operator fun invoke(champ: MostChamps): ChampInfo {
            return ChampInfo(
                name = champ.champName,
                winRate = champ.champWinRate,
                kda = champ.champKda,
            )
        }

        operator fun invoke(domain: ChampInfoDomainModel): ChampInfo {
            return ChampInfo(
                name = domain.name,
                winRate = domain.winRate,
                kda = domain.kda,
            )
        }

        operator fun invoke(domain: List<ChampInfoDomainModel>): List<ChampInfo> {
            return domain.map {
                invoke(it)
            }
        }
    }
}
