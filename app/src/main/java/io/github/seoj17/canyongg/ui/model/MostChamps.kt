package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.DomainMostChamps

data class MostChamps(
    val champName: String,
    val userPuuid: String,
    val champKda: Double,
    val champWinRate: Int,
) {
    companion object {
        operator fun invoke(domain: DomainMostChamps): MostChamps {
            return MostChamps(
                champName = domain.champName,
                userPuuid = domain.userPuuid,
                champKda = domain.champKda,
                champWinRate = domain.champWinRate,
            )
        }

        operator fun invoke(champs: List<DomainMostChamps>): List<MostChamps> {
            return champs.map {
                invoke(it)
            }
        }
    }
}
