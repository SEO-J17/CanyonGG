package io.github.seoj17.data.model

import io.github.seoj17.data.remote.response.summoner.SummonerResponse

data class SummonerDataModel(
    val id: String,
    val accountId: String,
    val puuid: String,
    val name: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val summonerLevel: Int,
) {
    companion object {
        operator fun invoke(response: SummonerResponse): SummonerDataModel {
            return SummonerDataModel(
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
