package io.github.seoj17.domain.model

import io.github.seoj17.data.local.summoner.SummonerInfoEntity
import io.github.seoj17.data.model.SummonerInfoDataModel

data class SummonerInfoDomainModel(
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
        operator fun invoke(data: SummonerInfoDataModel): SummonerInfoDomainModel {
            return SummonerInfoDomainModel(
                puuid = data.puuid,
                profile = data.profile,
                level = data.level,
                name = data.name,
                tier = data.tier,
                wholeMatch = data.wholeMatch,
                win = data.win,
                lose = data.lose,
                winRate = data.winRate,
                kda = data.kda,
                largestKill = data.largestKill,
            )
        }

        fun toEntity(domain: SummonerInfoDomainModel): SummonerInfoEntity {
            return SummonerInfoEntity(
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
    }
}
