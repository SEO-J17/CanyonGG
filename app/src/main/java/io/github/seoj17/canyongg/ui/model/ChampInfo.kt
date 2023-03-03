package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.ChampInfoDomainModel

data class ChampInfo(
    val name: String,
    val winRate: Int = 0,
    val kda: Double = 0.0,
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