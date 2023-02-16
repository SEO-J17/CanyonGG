package io.github.seoj17.canyongg.ui.detail.summaryTab

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.ItemParticipantBinding
import io.github.seoj17.canyongg.ui.model.ParticipantsMatches
import io.github.seoj17.canyongg.utils.setChampion
import io.github.seoj17.canyongg.utils.setItems
import io.github.seoj17.canyongg.utils.setRunes
import io.github.seoj17.canyongg.utils.setSpell

class WinParticipantsListViewHolder(
    private val binding: ItemParticipantBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(participant: ParticipantsMatches) {
        with(binding) {
            summonerName.text = participant.summonerName
            champion.setChampion(participant.championName)
            champLevel.text = participant.champLevel.toString()
            spell1.setSpell(participant.firstSpell)
            spell2.setSpell(participant.secondSpell)
            mainRune.setRunes(participant.mainPerk)
            subRune.setRunes(participant.subPerk)
            kill.text = kill.context.getString(R.string.summoner_kill, participant.kills)
            death.text = death.context.getString(R.string.summoner_death, participant.deaths)
            assist.text = assist.context.getString(R.string.summoner_assist, participant.assists)
            matchKda.text = matchKda.context.getString(R.string.summoner_kda, participant.kda)
            item1.setItems(participant.item0)
            item2.setItems(participant.item1)
            item3.setItems(participant.item2)
            item4.setItems(participant.item3)
            item5.setItems(participant.item4)
            item6.setItems(participant.item5)
            item7.setItems(participant.item6)
            csCount.text =
                csCount.context.getString(R.string.summoner_kill_cs, participant.totalMinionsKilled)
            damageBar.progress = participant.totalDamageDealtToChampions
            damageCnt.text = participant.totalDamageDealtToChampions.toString()
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): WinParticipantsListViewHolder {
            return WinParticipantsListViewHolder(
                ItemParticipantBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}