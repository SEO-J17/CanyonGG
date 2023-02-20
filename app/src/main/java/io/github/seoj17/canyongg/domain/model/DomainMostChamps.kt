package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.MyMostChampEntity

data class DomainMostChamps(
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
        operator fun invoke(entity: MyMostChampEntity): DomainMostChamps {
            return DomainMostChamps(
                userPuuid = entity.userPuuid,
                firstMostChamp = entity.firstMostChamp,
                secondMostChamp = entity.secondMostChamp,
                thirdMostChamp = entity.thirdMostChamp,
                firstChampKda = entity.firstChampKda,
                secondChampKda = entity.secondChampKda,
                thirdChampKda = entity.thirdChampKda,
                firstChampWinRate = entity.firstChampWinRate,
                secondChampWinRate = entity.secondChampWinRate,
                thirdChampWinRate = entity.thirdChampWinRate,
            )
        }
    }
}