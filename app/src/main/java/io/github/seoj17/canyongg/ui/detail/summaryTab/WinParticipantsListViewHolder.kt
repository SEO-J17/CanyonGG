package io.github.seoj17.canyongg.ui.detail.summaryTab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.databinding.ItemParticipantBinding
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

class WinParticipantsListViewHolder(
    private val binding: ItemParticipantBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(participant: SummonerMatchRecord, wholeData: MutableList<SummonerMatchRecord>) {
        with(binding) {
            data = participant
            damageBar.max = wholeData.maxOf { it.totalDealt }
            damageBar.progress = participant.totalDealt
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): WinParticipantsListViewHolder {
            return WinParticipantsListViewHolder(
                ItemParticipantBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
