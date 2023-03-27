package io.github.seoj17.data.local.match

import androidx.room.ColumnInfo
import androidx.room.Entity
import io.github.seoj17.data.remote.response.match.ParticipantResponse

@Entity(tableName = "match_info", primaryKeys = ["match_id", "puuid"])
data class MatchInfoEntity(
    @ColumnInfo(name = "match_id")
    val matchId: String,
    @ColumnInfo(name = "puuid")
    val puuid: String,
    @ColumnInfo(name = "summoner_name")
    val summonerName: String,
    @ColumnInfo(name = "champion_name")
    val championName: String,
    @ColumnInfo(name = "champion_level")
    val championLevel: Int,
    @ColumnInfo(name = "summoner_kda")
    val kda: Double,
    @ColumnInfo(name = "summoner_win")
    val win: Boolean,
    @ColumnInfo(name = "main_rune")
    val mainRune: Int,
    @ColumnInfo(name = "sub_rune")
    val subRune: Int,
    @ColumnInfo(name = "first_spell")
    val firstSpell: Int,
    @ColumnInfo(name = "second_spell")
    val secondSpell: Int,
    @ColumnInfo(name = "kills")
    val kill: Int,
    @ColumnInfo(name = "death")
    val death: Int,
    @ColumnInfo(name = "assist")
    val assist: Int,
    @ColumnInfo(name = "item1")
    val item1: Int,
    @ColumnInfo(name = "item2")
    val item2: Int,
    @ColumnInfo(name = "item3")
    val item3: Int,
    @ColumnInfo(name = "item4")
    val item4: Int,
    @ColumnInfo(name = "item5")
    val item5: Int,
    @ColumnInfo(name = "item6")
    val item6: Int,
    @ColumnInfo(name = "item7")
    val item7: Int,
    @ColumnInfo(name = "total_dealt")
    val totalDealt: Int,
    @ColumnInfo(name = "total_damaged")
    val totalDamaged: Int,
    @ColumnInfo(name = "vision_score")
    val visionScore: Int,
    @ColumnInfo(name = "minions_kill")
    val minions: Int,
    @ColumnInfo(name = "largest_kill")
    val largestMultiKill: Int,
    @ColumnInfo(name = "game_mode")
    val gameMode: String,
    @ColumnInfo(name = "spent_gold")
    val spentGold: Int,
    @ColumnInfo(name = "play_date")
    val playedDate: Long,
    @ColumnInfo(name = "play_time")
    val playedTime: Int,
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
