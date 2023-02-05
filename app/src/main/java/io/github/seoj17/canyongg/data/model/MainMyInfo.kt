package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.remote.response.ParticipantResponse

data class MainMyInfo(
    val assists: Int,
    val championName: String,
    val deaths: Int,
    val kills: Int,
    val puuid: String,
    val gameEndedInEarlySurrender: Boolean,
    val win: Boolean,
) {
    companion object {
        operator fun invoke(response: ParticipantResponse): MainMyInfo {
            return MainMyInfo(
                assists = response.assists,
                championName = response.championName,
                deaths = response.deaths,
                kills = response.kills,
                puuid = response.puuid,
                gameEndedInEarlySurrender = response.gameEndedInEarlySurrender,
                win = response.win,
            )
        }

        operator fun invoke(list: List<ParticipantResponse>): List<MainMyInfo> {
            return list.map { response ->
                invoke(response)
            }
        }
    }
}