package io.github.seoj17.canyongg.ui.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.ItemSummonerRecordBinding
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.utils.TimeFormatter
import io.github.seoj17.canyongg.utils.setChampion
import io.github.seoj17.canyongg.utils.setItems
import io.github.seoj17.canyongg.utils.setRunes
import io.github.seoj17.canyongg.utils.setSpell

class RecordListViewHolder(
    private val binding: ItemSummonerRecordBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(record: SummonerMatchRecord) {
        with(binding) {
            matchWin.text = if (record.win) "승리" else "패배"
            matchTime.text = TimeFormatter.formatTime(record.playedTime * 1000)
            champion.setChampion(record.championName)
            championLevel.text = record.championLevel.toString()
            spell1.setSpell(record.firstSpell)
            spell2.setSpell(record.secondSpell)
            mainRune.setRunes(record.mainRune)
            subRune.setRunes(record.subRune)
            kill.text = kill.context.getString(R.string.summoner_kill, record.kill)
            death.text = death.context.getString(R.string.summoner_death, record.death)
            assist.text = assist.context.getString(R.string.summoner_assist, record.assist)
            matchKda.text = matchKda.context.getString(R.string.summoner_kda, record.kda)
            item1.setItems(record.item1)
            item2.setItems(record.item2)
            item3.setItems(record.item3)
            item4.setItems(record.item4)
            item5.setItems(record.item5)
            item6.setItems(record.item6)
            item7.setItems(record.item7)
            matchType.text = record.gameMode
            matchDate.text = TimeFormatter.formatTime(record.playedDate)
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): RecordListViewHolder {
            return RecordListViewHolder(
                ItemSummonerRecordBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}