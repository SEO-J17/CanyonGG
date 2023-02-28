package io.github.seoj17.canyongg.data.local.match

import androidx.room.ColumnInfo
import androidx.room.Entity
import io.github.seoj17.canyongg.data.remote.response.match.ParticipantResponse

@Entity(tableName = "match_info", primaryKeys = ["match_id", "summoner_puuid"])
data class MatchInfoEntity(
    @ColumnInfo(name = "match_id")
    var matchId: String,
    @ColumnInfo(name = "summoner_puuid")
    var puuid: String,
    @ColumnInfo(name = "summoner_name")
    var summonerName: String,
    @ColumnInfo(name = "champion_name")
    var championName: String,
    @ColumnInfo(name = "champion_level")
    var championLevel: Int,
    @ColumnInfo(name = "summoner_kda")
    var kda: Double,
    @ColumnInfo(name = "summoner_win")
    var win: Boolean,
    @ColumnInfo(name = "main_rune")
    var mainRune: Int,
    @ColumnInfo(name = "sub_rune")
    var subRune: Int,
    @ColumnInfo(name = "first_spell")
    var firstSpell: Int,
    @ColumnInfo(name = "second_spell")
    var secondSpell: Int,
    @ColumnInfo(name = "summoner_kills")
    var kill: Int,
    @ColumnInfo(name = "summoner_death")
    var death: Int,
    @ColumnInfo(name = "summoner_assist")
    var assist: Int,
    @ColumnInfo(name = "item1")
    var item1: Int,
    @ColumnInfo(name = "item2")
    var item2: Int,
    @ColumnInfo(name = "item3")
    var item3: Int,
    @ColumnInfo(name = "item4")
    var item4: Int,
    @ColumnInfo(name = "item5")
    var item5: Int,
    @ColumnInfo(name = "item6")
    var item6: Int,
    @ColumnInfo(name = "item7")
    var item7: Int,
    @ColumnInfo(name = "total_dealt")
    var totalDealt: Int,
    @ColumnInfo(name = "total_damaged")
    var totalDamaged: Int,
    @ColumnInfo(name = "vision_score")
    var visionScore: Int,
    @ColumnInfo(name = "minions_kill")
    var minions: Int,
    @ColumnInfo(name = "largest_kill")
    var largestMultiKill: Int,
    @ColumnInfo(name = "game_mode")
    var gameMode: String,
    @ColumnInfo(name = "spent_gold")
    var spentGold: Int,
    @ColumnInfo(name = "play_date")
    var playedDate: Long,
    @ColumnInfo(name = "play_time")
    var playedTime: Int,
) {
    companion object {
        operator fun invoke(
            response: ParticipantResponse,
            gameCreation: Long,
            matchId: String,
            gameMode: String,
        ): MatchInfoEntity {
            return MatchInfoEntity(
                puuid = response.puuid,
                matchId = matchId,
                summonerName = response.summonerName,
                championLevel = response.champLevel,
                championName = response.championName,
                kda = response.challenges.kda,
                win = response.win,
                mainRune = response.perks.styles[0].selections[0].perk,
                subRune = response.perks.styles[1].style,
                firstSpell = response.summoner1Id,
                secondSpell = response.summoner2Id,
                kill = response.kills,
                death = response.deaths,
                assist = response.assists,
                item1 = response.item0,
                item2 = response.item1,
                item3 = response.item2,
                item4 = response.item3,
                item5 = response.item4,
                item6 = response.item5,
                item7 = response.item6,
                totalDealt = response.totalDamageDealtToChampions,
                totalDamaged = response.totalDamageTaken,
                visionScore = response.visionScore,
                minions = response.totalMinionsKilled,
                largestMultiKill = response.largestMultiKill,
                gameMode = gameMode,
                spentGold = response.goldSpent,
                playedDate = gameCreation,
                playedTime = response.timePlayed,
            )
        }

        operator fun invoke(
            response: List<ParticipantResponse>,
            gameCreation: Long,
            matchId: String,
            gameMode: String,
        ): List<MatchInfoEntity> {
            return response.map {
                invoke(it, gameCreation, matchId, gameMode)
            }
        }
    }
}
