package io.github.seoj17.canyongg.ui.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.databinding.ItemSummonerRecordBinding
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

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