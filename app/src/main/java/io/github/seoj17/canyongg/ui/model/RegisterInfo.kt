package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.RegisterInfoDomainModel

data class RegisterInfo(
    val assists: Int,
    val championName: String,
    val deaths: Int,
    val kills: Int,
    val puuid: String,
    val gameEndedInEarlySurrender: Boolean,
    val win: Boolean,
    val largestKill: Int,
) {
    companion object {
        operator fun invoke(domain: RegisterInfoDomainModel): RegisterInfo {
            return RegisterInfo(
                assists = domain.assists,
                championName = domain.championName,
                deaths = domain.deaths,
                kills = domain.kills,
                puuid = domain.puuid,
                gameEndedInEarlySurrender = domain.gameEndedInEarlySurrender,
                win = domain.win,
                largestKill = domain.largestKill
            )
        }

        operator fun invoke(domain: List<RegisterInfoDomainModel>): List<RegisterInfo> {
            return domain.map {
                invoke(it)
            }
        }
    }
}