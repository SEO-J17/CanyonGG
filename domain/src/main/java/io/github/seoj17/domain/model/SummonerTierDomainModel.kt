package io.github.seoj17.domain.model

import io.github.seoj17.data.model.SummonerTierDataModel

data class SummonerTierDomainModel(
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
        operator fun invoke(data: SummonerTierDataModel): SummonerTierDomainModel {
            return SummonerTierDomainModel(
                leagueId = data.leagueId,
                leaguePoints = data.leaguePoints,
                losses = data.losses,
                queueType = data.queueType,
                rank = data.rank,
                summonerName = data.summonerName,
                tier = data.tier,
                wins = data.wins,
            )
        }
    }
}
