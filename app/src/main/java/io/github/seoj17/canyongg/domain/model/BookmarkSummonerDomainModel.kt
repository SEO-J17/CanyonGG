package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkEntity
import io.github.seoj17.canyongg.data.model.SummonerBookmarkDataModel

data class BookmarkSummonerDomainModel(
    val puuid: String,
    val summonerName: String,
    val summonerLevel: Int,
    val summonerIcon: Int,
) {
    companion object {
        operator fun invoke(data: SummonerBookmarkDataModel): BookmarkSummonerDomainModel {
            return BookmarkSummonerDomainModel(
                puuid = data.puuid,
                summonerName = data.summonerName,
                summonerLevel = data.level,
                summonerIcon = data.icon,
            )
        }

        operator fun invoke(data: List<SummonerBookmarkDataModel>): List<BookmarkSummonerDomainModel> {
            return data.map {
                invoke(it)
            }
        }

        fun toEntity(domain: BookmarkSummonerDomainModel): SummonerBookmarkEntity {
            return SummonerBookmarkEntity(
                puuid = domain.puuid,
                summonerName = domain.summonerName,
                level = domain.summonerLevel,
                icon = domain.summonerIcon,
            )
        }
    }
}