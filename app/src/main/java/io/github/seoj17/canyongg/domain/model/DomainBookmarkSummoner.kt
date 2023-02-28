package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkEntity

data class DomainBookmarkSummoner(
    val summonerPuuid: String,
    val summonerName: String,
    val summonerLevel: Int,
    val summonerIcon: Int,
) {
    companion object {
        operator fun invoke(entity: SummonerBookmarkEntity): DomainBookmarkSummoner {
            return DomainBookmarkSummoner(
                summonerPuuid = entity.summonerPuuid,
                summonerName = entity.summonerName,
                summonerLevel = entity.summonerLevel,
                summonerIcon = entity.summonerIcon,
            )
        }

        operator fun invoke(entity: List<SummonerBookmarkEntity>): List<DomainBookmarkSummoner> {
            return entity.map {
                invoke(it)
            }
        }
    }
}