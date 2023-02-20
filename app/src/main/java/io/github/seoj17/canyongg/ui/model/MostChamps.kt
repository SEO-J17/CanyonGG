package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.DomainMostChamps

data class MostChamps(
    val userPuuid: String,
    val firstMostChamp: String,
    val secondMostChamp: String,
    val thirdMostChamp: String,
    val firstChampKda: Double,
    val secondChampKda: Double,
    val thirdChampKda: Double,
    val firstChampWinRate: Int,
    val secondChampWinRate: Int,
    val thirdChampWinRate: Int,
) {
    companion object {
        operator fun invoke(domain: DomainMostChamps): MostChamps {
            return MostChamps(
                userPuuid = domain.userPuuid,
                firstMostChamp = domain.firstMostChamp,
                secondMostChamp = domain.secondMostChamp,
                thirdMostChamp = domain.thirdMostChamp,
                firstChampKda = domain.firstChampKda,
                secondChampKda = domain.secondChampKda,
                thirdChampKda = domain.thirdChampKda,
                firstChampWinRate = domain.firstChampWinRate,
                secondChampWinRate = domain.secondChampWinRate,
                thirdChampWinRate = domain.thirdChampWinRate,
            )
        }
    }
}