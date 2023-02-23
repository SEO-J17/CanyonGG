package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.MatchInfoEntity
import io.github.seoj17.canyongg.data.model.DataMatches

data class DomainMatches(
    val puuid: String,
    val matchId: String,
    val summonerName: String,
    val championName: String,
    val championLevel: Int,
    val kda: Double,
    val win: Boolean,
    val mainRune: String,
    val subRune: String,
    val firstSpell: String,
    val secondSpell: String,
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
            data: DataMatches,
            firstSpell: String,
            secondSpell: String,
            mainRune: String,
            subRune: String,
        ): DomainMatches {
            return DomainMatches(
                puuid = data.puuid,
                matchId = data.matchId,
                summonerName = data.summonerName,
                championLevel = data.championLevel,
                championName = data.championName,
                kda = data.kda,
                win = data.win,
                mainRune = mainRune,
                subRune = subRune,
                firstSpell = firstSpell,
                secondSpell = secondSpell,
                kill = data.kill,
                death = data.death,
                assist = data.assist,
                item1 = data.item1,
                item2 = data.item2,
                item3 = data.item3,
                item4 = data.item4,
                item5 = data.item5,
                item6 = data.item6,
                item7 = data.item7,
                totalDealt = data.totalDealt,
                totalDamaged = data.totalDamaged,
                wardPlaced = data.wardPlaced,
                minions = data.minions,
                largestMultiKill = data.largestMultiKill,
                gameMode = data.gameMode,
                spentGold = data.spentGold,
                playedDate = data.playedDate,
                playedTime = data.playedTime,
            )
        }

        operator fun invoke(
            entity: MatchInfoEntity,
            firstSpell: String,
            secondSpell: String,
            mainRune: String,
            subRune: String,
        ): DomainMatches {
            return DomainMatches(
                puuid = entity.puuid,
                matchId = entity.matchId,
                summonerName = entity.summonerName,
                championLevel = entity.championLevel,
                championName = entity.championName,
                kda = entity.kda,
                win = entity.win,
                mainRune = mainRune,
                subRune = subRune,
                firstSpell = firstSpell,
                secondSpell = secondSpell,
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
    }
}