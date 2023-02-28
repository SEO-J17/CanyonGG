package io.github.seoj17.canyongg.ui.detail.summaryTab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.databinding.ItemParticipantBinding
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

class LoseParticipantsListViewHolder(
    private val binding: ItemParticipantBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(participant: SummonerMatchRecord, wholeData: MutableList<SummonerMatchRecord>) {
        with(binding) {
            data = participant
            damageBar.progress = participant.totalDealt
            damageBar.max = wholeData.maxOf { it.totalDealt }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): LoseParticipantsListViewHolder {
            return LoseParticipantsListViewHolder(
                ItemParticipantBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}