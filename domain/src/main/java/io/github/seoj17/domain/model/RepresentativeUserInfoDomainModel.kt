package io.github.seoj17.domain.model

import io.github.seoj17.data.local.user.RegisterUserInfoEntity
import io.github.seoj17.data.model.RegisterUserDataModel

data class RepresentativeUserInfoDomainModel(
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
        operator fun invoke(data: RegisterUserDataModel): RepresentativeUserInfoDomainModel {
            return RepresentativeUserInfoDomainModel(
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
            )
        }

        fun toEntity(domain: RepresentativeUserInfoDomainModel): RegisterUserInfoEntity {
            return RegisterUserInfoEntity(
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
        
        fun toUserSummaryModel(
            summoner: SummonerDomainModel,
            record: UserRecordDomainModel,
            tier: SummonerTierDomainModel?,
        ): RepresentativeUserInfoDomainModel {
            return RepresentativeUserInfoDomainModel(
                puuid = summoner.puuid,
                profile = summoner.profileIconId,
                level = summoner.summonerLevel,
                name = summoner.name,
                tier = tier?.let {
                    "${it.tier} ${it.rank}"
                } ?: "Unranked",
                wholeMatch = record.wholeMatch,
                win = record.winCount,
                lose = record.loseCount,
                winRate = record.winRate,
                kda = record.kda,
            )
        }
    }
}
