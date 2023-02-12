package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.remote.response.summoner.SummonerResponse

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
        operator fun invoke(response: SummonerResponse): Summoner {
            return Summoner(
                id = response.id,
                accountId = response.accountId,
                puuid = response.puuid,
                name = response.name,
                profileIconId = response.profileIconId,
                revisionDate = response.revisionDate,
                summonerLevel = response.summonerLevel,
            )
        }
    }
}
