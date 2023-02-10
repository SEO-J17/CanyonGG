package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.model.DataMatches
import io.github.seoj17.canyongg.data.remote.response.match.Challenges

data class DomainMatches(
    val puuid: String,
    val matchId: String,
    val participants: List<String>,
    val champName: String,
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
    val mainPerk: String,
    val subPerk: String,
    val firstSpell: String,
    val secondSpell: String,
    val largestMultiKill: Int,
    val timePlayed: Int,
    val win: Boolean
) {
    companion object {
        operator fun invoke(
            dataMatches: DataMatches,
            firstSpell: String,
            secondSpell: String,
            mainPerk: String,
            subPerk: String,
            champName: String,
        ): DomainMatches {
            return DomainMatches(
                puuid = dataMatches.puuid,
                matchId = dataMatches.matchId,
                participants = dataMatches.participants,
                champName = champName,
                champLevel = dataMatches.champLevel,
                challenges = dataMatches.challenges,
                gameMode = dataMatches.gameMode,
                gameCreation = dataMatches.gameCreation,
                kills = dataMatches.kills,
                assists = dataMatches.kills,
                deaths = dataMatches.deaths,
                item0 = dataMatches.item0,
                item1 = dataMatches.item1,
                item2 = dataMatches.item2,
                item3 = dataMatches.item3,
                item4 = dataMatches.item4,
                item5 = dataMatches.item5,
                item6 = dataMatches.item6,
                kda = dataMatches.kda,
                mainPerk = mainPerk,
                subPerk = subPerk,
                firstSpell = firstSpell,
                secondSpell = secondSpell,
                largestMultiKill = dataMatches.largestMultiKill,
                timePlayed = dataMatches.timePlayed,
                win = dataMatches.win,
            )
        }
    }
}