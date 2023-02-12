package io.github.seoj17.canyongg.ui.model

import android.annotation.SuppressLint
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import io.github.seoj17.canyongg.data.remote.response.match.Challenges
import io.github.seoj17.canyongg.domain.model.DomainMatches

data class SummonerMatchRecord(
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
        val diffUtil = object : DiffUtil.ItemCallback<SummonerMatchRecord>() {
            override fun areItemsTheSame(
                oldItem: SummonerMatchRecord,
                newItem: SummonerMatchRecord,
            ): Boolean {
                return oldItem.matchId == newItem.matchId
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: SummonerMatchRecord,
                newItem: SummonerMatchRecord,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: DomainMatches): SummonerMatchRecord {
            return SummonerMatchRecord(
                puuid = domain.puuid,
                matchId = domain.matchId,
                participants = domain.participants,
                champName = domain.champName,
                champLevel = domain.champLevel,
                challenges = domain.challenges,
                gameMode = domain.gameMode,
                gameCreation = domain.gameCreation,
                kills = domain.kills,
                assists = domain.kills,
                deaths = domain.deaths,
                item0 = domain.item0,
                item1 = domain.item1,
                item2 = domain.item2,
                item3 = domain.item3,
                item4 = domain.item4,
                item5 = domain.item5,
                item6 = domain.item6,
                kda = domain.kda,
                mainPerk = domain.mainPerk,
                subPerk = domain.subPerk,
                firstSpell = domain.firstSpell,
                secondSpell = domain.secondSpell,
                largestMultiKill = domain.largestMultiKill,
                timePlayed = domain.timePlayed,
                win = domain.win,
            )
        }

        operator fun invoke(paging: PagingData<DomainMatches>): PagingData<SummonerMatchRecord> {
            return paging.map { data ->
                invoke(data)
            }
        }
    }
}