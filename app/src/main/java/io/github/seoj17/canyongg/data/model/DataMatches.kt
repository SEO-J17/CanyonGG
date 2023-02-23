package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.local.MatchInfoEntity

class DataMatches(
    val puuid: String,
    val matchId: String,
    val summonerName: String,
    val championName: String,
    val championLevel: Int,
    val kda: Double,
    val win: Boolean,
    val mainRune: Int,
    val subRune: Int,
    val firstSpell: Int,
    val secondSpell: Int,
    val kill: Int,
    val death: Int,
    val assist: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,
    val item7: Int,
    val totalDealt: Int,
    val totalDamaged: Int,
    val wardPlaced: Int,
    val minions: Int,
    val largestMultiKill: Int,
    val gameMode: String,
    val spentGold: Int,
    val playedDate: Long,
    val playedTime: Int,
) {
    companion object {
        operator fun invoke(
            entity: MatchInfoEntity,
        ): DataMatches {
            return DataMatches(
                puuid = entity.puuid,
                matchId = entity.matchId,
                summonerName = entity.summonerName,
                championLevel = entity.championLevel,
                championName = entity.championName,
                kda = entity.kda,
                win = entity.win,
                mainRune = entity.mainRune,
                subRune = entity.subRune,
                firstSpell = entity.firstSpell,
                secondSpell = entity.secondSpell,
                kill = entity.kill,
                death = entity.death,
                assist = entity.assist,
                item1 = entity.item1,
                item2 = entity.item2,
                item3 = entity.item3,
                item4 = entity.item4,
                item5 = entity.item5,
                item6 = entity.item6,
                item7 = entity.item7,
                totalDealt = entity.totalDealt,
                totalDamaged = entity.totalDamaged,
                wardPlaced = entity.wardPlaced,
                minions = entity.minions,
                largestMultiKill = entity.largestMultiKill,
                gameMode = entity.gameMode,
                spentGold = entity.spentGold,
                playedDate = entity.playedDate,
                playedTime = entity.playedTime,
            )
        }

        operator fun invoke(
            matchInfo: List<MatchInfoEntity>,
        ): List<DataMatches> {
            return matchInfo.map {
                invoke(it)
            }
        }
    }
}
