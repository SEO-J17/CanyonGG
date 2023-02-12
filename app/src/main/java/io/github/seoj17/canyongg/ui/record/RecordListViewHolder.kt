package io.github.seoj17.canyongg.ui.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.databinding.ItemSummonerRecordBinding
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
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
            matchTime.text = record.timePlayed.toString()
            champion.setChampion(record.champName)
            championLevel.text = record.champLevel.toString()
            spell1.setSpell(record.firstSpell)
            spell2.setSpell(record.secondSpell)
            mainRune.setRunes(record.mainPerk)
            subRune.setRunes(record.subPerk)
            kill.text = record.kills.toString()
            death.text = record.deaths.toString()
            assist.text = record.assists.toString()
            matchKda.text = "${String.format("%.2f", record.kda)} : 1"
            item1.setItems(record.item0)
            item2.setItems(record.item1)
            item3.setItems(record.item2)
            item4.setItems(record.item3)
            item5.setItems(record.item4)
            item6.setItems(record.item5)
            item7.setItems(record.item6)
            matchType.text = record.gameMode
            matchDate.text = record.gameCreation.toString()
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