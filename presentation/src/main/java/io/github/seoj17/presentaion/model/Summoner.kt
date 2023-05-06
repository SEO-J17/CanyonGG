package io.github.seoj17.presentaion.model

import io.github.seoj17.domain.model.SummonerDomainModel

data class Summoner(
    val id: String,
    val accountId: String,
    val puuid: String,
    val name: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val summonerLevel: Int,
) {
    companion object {
        operator fun invoke(domain: SummonerDomainModel): Summoner {
            return Summoner(
                id = domain.id,
                accountId = domain.accountId,
                puuid = domain.puuid,
                name = domain.name,
                profileIconId = domain.profileIconId,
                revisionDate = domain.revisionDate,
                summonerLevel = domain.summonerLevel,
            )
        }
    }
}
