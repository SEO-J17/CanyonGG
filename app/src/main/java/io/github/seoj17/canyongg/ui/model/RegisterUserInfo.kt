package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.RepresentativeUserInfoDomainModel

data class RegisterUserInfo(
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
) {
    companion object {
        operator fun invoke(domain: RepresentativeUserInfoDomainModel): RegisterUserInfo {
            return RegisterUserInfo(
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
            )
        }

        fun toDomainModel(
            summoner: Summoner,
            record: UserRecord,
            tier: String,
        ): RepresentativeUserInfoDomainModel {
            return RepresentativeUserInfoDomainModel(
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
            )
        }
    }
}
