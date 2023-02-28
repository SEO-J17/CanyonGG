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
            data = record
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): RecordListViewHolder {
            return RecordListViewHolder(ItemSummonerRecordBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
        }
    }
}