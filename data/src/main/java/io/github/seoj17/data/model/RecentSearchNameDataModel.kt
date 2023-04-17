package io.github.seoj17.data.model

import io.github.seoj17.data.local.recent.RecentSearchNameEntity

data class RecentSearchNameDataModel(
    val summonerPuuid: String,
    val summonerName: String,
) {
    companion object {
        operator fun invoke(entity: RecentSearchNameEntity): RecentSearchNameDataModel {
            return RecentSearchNameDataModel(
                summonerPuuid = entity.summonerPuuid,
                summonerName = entity.summonerName,
            )
        }

        operator fun invoke(entities: List<RecentSearchNameEntity>): List<RecentSearchNameDataModel> {
            return entities.map {
                invoke(it)
            }
        }
    }
}
