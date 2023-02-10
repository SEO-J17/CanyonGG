package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.data.remote.response.match.Challenges
import io.github.seoj17.canyongg.data.remote.response.match.Perks
import io.github.seoj17.canyongg.domain.model.DomainSummonerMatchInfo

data class ParticipantsMatches(
    val assists: Int,
    val baronKills: Int,
    val challenges: Challenges,
    val champLevel: Int,
    val championId: Int,
    val championName: String,
    val deaths: Int,
    val detectorWardsPlaced: Int,
    val gameEndedInEarlySurrender: Boolean,
    val gameEndedInSurrender: Boolean,
    val goldEarned: Int,
    val goldSpent: Int,
    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,
    val kills: Int,
    val largestMultiKill: Int,
    val participantId: Int,
    val perks: Perks,
    val puuid: String,
    val sightWardsBoughtInGame: Int,
    val summonerId: String,
    val summonerLevel: Int,
    val summonerName: String,
    val teamEarlySurrendered: Boolean,
    val teamId: Int,
    val teamPosition: String,
    val timePlayed: Int,
    val totalDamageDealt: Int,
    val totalDamageDealtToChampions: Int,
    val totalDamageShieldedOnTeammates: Int,
    val totalDamageTaken: Int,
    val visionScore: Int,
    val visionWardsBoughtInGame: Int,
    val wardsKilled: Int,
    val wardsPlaced: Int,
    val win: Boolean
) {
    companion object {
        operator fun invoke(domain: DomainSummonerMatchInfo): ParticipantsMatches {
            return ParticipantsMatches(
                assists = domain.assists,
                baronKills = domain.baronKills,
                challenges = domain.challenges,
                champLevel = domain.champLevel,
                championId = domain.championId,
                championName = domain.championName,
                deaths = domain.deaths,
                detectorWardsPlaced = domain.detectorWardsPlaced,
                gameEndedInEarlySurrender = domain.gameEndedInEarlySurrender,
                gameEndedInSurrender = domain.gameEndedInSurrender,
                goldEarned = domain.goldEarned,
                goldSpent = domain.goldSpent,
                item0 = domain.item0,
                item1 = domain.item1,
                item2 = domain.item2,
                item3 = domain.item3,
                item4 = domain.item4,
                item5 = domain.item5,
                item6 = domain.item6,
                kills = domain.kills,
                largestMultiKill = domain.largestMultiKill,
                participantId = domain.participantId,
                perks = domain.perks,
                puuid = domain.puuid,
                sightWardsBoughtInGame = domain.sightWardsBoughtInGame,
                summonerId = domain.summonerId,
                summonerLevel = domain.summonerLevel,
                summonerName = domain.summonerName,
                teamEarlySurrendered = domain.teamEarlySurrendered,
                teamId = domain.teamId,
                teamPosition = domain.teamPosition,
                timePlayed = domain.timePlayed,
                totalDamageDealt = domain.totalDamageDealt,
                totalDamageDealtToChampions = domain.totalDamageDealtToChampions,
                totalDamageShieldedOnTeammates = domain.totalDamageShieldedOnTeammates,
                totalDamageTaken = domain.totalDamageTaken,
                visionScore = domain.visionScore,
                visionWardsBoughtInGame = domain.visionWardsBoughtInGame,
                wardsKilled = domain.wardsKilled,
                wardsPlaced = domain.wardsPlaced,
                win = domain.win,
            )
        }

        operator fun invoke(domain: List<DomainSummonerMatchInfo>): List<ParticipantsMatches> {
            return domain.map {
                invoke(it)
            }
        }
    }
}