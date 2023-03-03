package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.remote.response.match.ParticipantResponse

data class RegisterInfoDomainModel(
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
        operator fun invoke(response: ParticipantResponse): RegisterInfoDomainModel {
            return RegisterInfoDomainModel(
                assists = response.assists,
                championName = response.championName,
                deaths = response.deaths,
                kills = response.kills,
                puuid = response.puuid,
                gameEndedInEarlySurrender = response.gameEndedInEarlySurrender,
                win = response.win,
                largestKill = response.largestMultiKill
            )
        }

        operator fun invoke(list: List<ParticipantResponse>): List<RegisterInfoDomainModel> {
            return list.map { response ->
                invoke(response)
            }
        }
    }
}