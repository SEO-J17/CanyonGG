package io.github.seoj17.data.model

import io.github.seoj17.data.remote.response.summoner.TierResponse

data class SummonerTierDataModel(
    val leagueId: String,
    val leaguePoints: Int,
    val losses: Int,
    val queueType: String,
    val rank: String,
    val summonerName: String,
    val tier: String,
    val wins: Int,
) {
    companion object {
        operator fun invoke(response: TierResponse): SummonerTierDataModel {
            return SummonerTierDataModel(
                leagueId = response.leagueId,
                leaguePoints = response.leaguePoints,
                losses = response.losses,
                queueType = response.queueType,
                rank = response.rank,
                summonerName = response.summonerName,
                tier = response.tier,
                wins = response.wins,
            )
        }
    }
}
