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
            matchTime.text = TimeFormatter.formatTime(record.timePlayed * 1000)
            champion.setChampion(record.champName)
            championLevel.text = record.champLevel.toString()
            spell1.setSpell(record.firstSpell)
            spell2.setSpell(record.secondSpell)
            mainRune.setRunes(record.mainPerk)
            subRune.setRunes(record.subPerk)
            kill.text = kill.context.getString(R.string.summoner_kill, record.kills)
            death.text = death.context.getString(R.string.summoner_death, record.deaths)
            assist.text = assist.context.getString(R.string.summoner_assist, record.assists)
            matchKda.text = matchKda.context.getString(R.string.summoner_kda, record.kda)
            item1.setItems(record.item0)
            item2.setItems(record.item1)
            item3.setItems(record.item2)
            item4.setItems(record.item3)
            item5.setItems(record.item4)
            item6.setItems(record.item5)
            item7.setItems(record.item6)
            matchType.text = record.gameMode
            matchDate.text = TimeFormatter.formatTime(record.gameCreation)
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