package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkEntity


data class SummonerBookmarkDataModel(
    val puuid: String,
    val summonerName: String,
    val level: Int,
    val icon: Int,
) {
    companion object {
        operator fun invoke(entity: SummonerBookmarkEntity): SummonerBookmarkDataModel {
            return SummonerBookmarkDataModel(
                puuid = entity.puuid,
                summonerName = entity.summonerName,
                level = entity.level,
                icon = entity.icon,
            )
        }

        operator fun invoke(entities: List<SummonerBookmarkEntity>): List<SummonerBookmarkDataModel> {
            return entities.map {
                invoke(it)
            }
        }
    }
}