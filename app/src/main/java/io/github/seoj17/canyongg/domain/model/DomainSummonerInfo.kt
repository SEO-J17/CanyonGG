package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoEntity

data class DomainSummonerInfo(
    val puuid: String,
    val profile: Int,
    val level: Int,
    val name: String,
    val tier: String,
    val wholeMatch: Int,
    val win: Int,
    val lose: Int,
    val winRate: Int,
    val kda: Double,
    val largestKill: Int,
) {
    companion object {
        operator fun invoke(entity: SummonerInfoEntity): DomainSummonerInfo {
            return DomainSummonerInfo(
                puuid = entity.puuid,
                profile = entity.profile,
                level = entity.level,
                name = entity.name,
                tier = entity.tier,
                wholeMatch = entity.wholeMatch,
                win = entity.win,
                lose = entity.lose,
                winRate = entity.winRate,
                kda = entity.kda,
                largestKill = entity.largestKill
            )
        }
    }
}