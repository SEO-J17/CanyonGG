package io.github.seoj17.presentation.ui.detail.summaryTab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.presentation.model.SummonerMatchRecord
import io.github.seoj17.presentation.databinding.ItemParticipantBinding

class LoseParticipantsListViewHolder(
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
        operator fun invoke(parent: ViewGroup): LoseParticipantsListViewHolder {
            return LoseParticipantsListViewHolder(
                ItemParticipantBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
