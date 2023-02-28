package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.recent.search.RecentSearchNameEntity

data class DomainRecentSummoner(
    val name: String,
    val puuid: String,
) {
    companion object {
        operator fun invoke(entity: RecentSearchNameEntity): DomainRecentSummoner {
            return DomainRecentSummoner(
                name = entity.summonerName,
                puuid = entity.summonerPuuid,
            )
        }

        operator fun invoke(entity: List<RecentSearchNameEntity>): List<DomainRecentSummoner> {
            return entity.map {
                invoke(it)
            }
        }
    }
}