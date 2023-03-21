package io.github.seoj17.canyongg.ui.model

import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import io.github.seoj17.canyongg.domain.model.MatchInfoDomainModel

data class SummonerMatchRecord(
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
    val visionScore: Int,
    val minions: Int,
    val largestMultiKill: Int,
    val gameMode: String,
    val spentGold: Int,
    val playedDate: Long,
    val playedTime: Int,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SummonerMatchRecord>() {
            override fun areItemsTheSame(
                oldItem: SummonerMatchRecord,
                newItem: SummonerMatchRecord,
            ): Boolean {
                return oldItem.matchId == newItem.matchId
            }

            override fun areContentsTheSame(
                oldItem: SummonerMatchRecord,
                newItem: SummonerMatchRecord,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: MatchInfoDomainModel): SummonerMatchRecord {
            return SummonerMatchRecord(
                puuid = domain.puuid,
                matchId = domain.matchId,
                summonerName = domain.summonerName,
                championLevel = domain.championLevel,
                championName = domain.championName,
                kda = domain.kda,
                win = domain.win,
                mainRune = domain.mainRune,
                subRune = domain.subRune,
                firstSpell = domain.firstSpell,
                secondSpell = domain.secondSpell,
                kill = domain.kill,
                death = domain.death,
                assist = domain.assist,
                item1 = domain.item1,
                item2 = domain.item2,
                item3 = domain.item3,
                item4 = domain.item4,
                item5 = domain.item5,
                item6 = domain.item6,
                item7 = domain.item7,
                totalDealt = domain.totalDealt,
                totalDamaged = domain.totalDamaged,
                visionScore = domain.visionScore,
                minions = domain.minions,
                largestMultiKill = domain.largestMultiKill,
                gameMode = domain.gameMode,
                spentGold = domain.spentGold,
                playedDate = domain.playedDate,
                playedTime = domain.playedTime,
            )
        }

        operator fun invoke(paging: PagingData<MatchInfoDomainModel>): PagingData<SummonerMatchRecord> {
            return paging.map { data ->
                invoke(data)
            }
        }

        operator fun invoke(list: List<MatchInfoDomainModel>): List<SummonerMatchRecord> {
            return list.map {
                invoke(it)
            }
        }
    }
}
