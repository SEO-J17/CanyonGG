package io.github.seoj17.canyongg.ui.detail.summaryTab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.ItemParticipantBinding
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.utils.setChampion
import io.github.seoj17.canyongg.utils.setItems
import io.github.seoj17.canyongg.utils.setRunes
import io.github.seoj17.canyongg.utils.setSpell

class WinParticipantsListViewHolder(
    private val binding: ItemParticipantBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(participant: SummonerMatchRecord) {
        with(binding) {
            summonerName.text = participant.summonerName
            champion.setChampion(participant.championName)
            champLevel.text = participant.championLevel.toString()
            spell1.setSpell(participant.firstSpell)
            spell2.setSpell(participant.secondSpell)
            mainRune.setRunes(participant.mainRune)
            subRune.setRunes(participant.subRune)
            kill.text = kill.context.getString(R.string.summoner_kill, participant.kill)
            death.text = death.context.getString(R.string.summoner_death, participant.death)
            assist.text = assist.context.getString(R.string.summoner_assist, participant.assist)
            matchKda.text = matchKda.context.getString(R.string.summoner_kda, participant.kda)
            item1.setItems(participant.item1)
            item2.setItems(participant.item2)
            item3.setItems(participant.item3)
            item4.setItems(participant.item4)
            item5.setItems(participant.item5)
            item6.setItems(participant.item6)
            item7.setItems(participant.item7)
            csCount.text =
                csCount.context.getString(R.string.summoner_kill_cs, participant.minions)
            damageBar.progress = participant.totalDealt
            damageCnt.text = participant.totalDamaged.toString()
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