package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.SummonerInfoDomainModel

data class SummonerInfo(
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
        operator fun invoke(domain: SummonerInfoDomainModel): SummonerInfo {
            return SummonerInfo(
                puuid = domain.puuid,
                profile = domain.profile,
                level = domain.level,
                name = domain.name,
                tier = domain.tier,
                wholeMatch = domain.wholeMatch,
                win = domain.win,
                lose = domain.lose,
                winRate = domain.winRate,
                kda = domain.kda,
                largestKill = domain.largestKill,
            )
        }

        fun toDomainModel(
            summoner: Summoner,
            record: UserRecord,
            tier: String,
        ): SummonerInfoDomainModel {
            return SummonerInfoDomainModel(
                puuid = summoner.puuid,
                profile = summoner.profileIconId,
                level = summoner.summonerLevel,
                name = summoner.name,
                tier = tier,
                wholeMatch = record.wholeMatch,
                win = record.winCount,
                lose = record.loseCount,
                winRate = record.winRate,
                kda = record.kda,
                largestKill = record.largestKill,
            )
        }
    }
}