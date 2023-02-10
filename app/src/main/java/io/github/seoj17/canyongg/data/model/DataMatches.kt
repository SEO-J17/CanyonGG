package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.remote.response.match.Challenges
import io.github.seoj17.canyongg.data.remote.response.match.MatchInfoResponse
import io.github.seoj17.canyongg.data.remote.response.match.ParticipantResponse
import io.github.seoj17.canyongg.data.remote.response.match.Perks

class DataMatches(
    val puuid: String,
    val matchId: String,
    val participants: List<String>,
    val championId: Int,
    val champLevel: Int,
    val challenges: Challenges,
    val gameMode: String,
    val gameCreation: Long,
    val kills: Int,
    val assists: Int,
    val deaths: Int,
    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,
    val kda: Double,
    val perks: Perks,
    val summoner1Id: Int,
    val summoner2Id: Int,
    val largestMultiKill: Int,
    val timePlayed: Int,
    val win: Boolean
) {
    companion object {
        operator fun invoke(
            matchInfo: MatchInfoResponse,
            participant: ParticipantResponse,
            summonerPuuid: String
        ): DataMatches {
            return DataMatches(
                puuid = summonerPuuid,
                matchId = matchInfo.metadata.matchId,
                participants = matchInfo.metadata.participants,
                championId = participant.championId,
                champLevel = participant.champLevel,
                challenges = participant.challenges,
                gameMode = matchInfo.info.gameMode,
                gameCreation = matchInfo.info.gameCreation,
                kills = participant.kills,
                assists = participant.kills,
                deaths = participant.deaths,
                item0 = participant.item0,
                item1 = participant.item1,
                item2 = participant.item2,
                item3 = participant.item3,
                item4 = participant.item4,
                item5 = participant.item5,
                item6 = participant.item6,
                perks = participant.perks,
                kda = participant.challenges.kda,
                summoner1Id = participant.summoner1Id,
                summoner2Id = participant.summoner2Id,
                largestMultiKill = participant.largestMultiKill,
                timePlayed = participant.timePlayed,
                win = participant.win,
            )
        }

        operator fun invoke(
            matchInfo: List<MatchInfoResponse>,
            summonerPuuid: String
        ): List<DataMatches> {
            return matchInfo.map {
                invoke(
                    it,
                    it.info.participants.find { it.puuid == summonerPuuid }!!,
                    summonerPuuid,
                )
            }
        }
    }
}
