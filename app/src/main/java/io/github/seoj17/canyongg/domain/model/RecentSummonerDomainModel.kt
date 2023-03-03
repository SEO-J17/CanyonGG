package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.model.RecentSearchNameDataModel

data class RecentSummonerDomainModel(
    val name: String,
    val puuid: String,
) {
    companion object {
        operator fun invoke(data: RecentSearchNameDataModel): RecentSummonerDomainModel {
            return RecentSummonerDomainModel(
                name = data.summonerName,
                puuid = data.summonerPuuid,
            )
        }

        operator fun invoke(data: List<RecentSearchNameDataModel>): List<RecentSummonerDomainModel> {
            return data.map {
                invoke(it)
            }
        }
    }
}