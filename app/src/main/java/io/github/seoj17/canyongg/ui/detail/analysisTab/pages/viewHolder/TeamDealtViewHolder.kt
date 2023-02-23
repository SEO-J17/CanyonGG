package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.databinding.ItemTeamAnalysisBinding
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.utils.setChampion

class TeamDealtViewHolder(
    private val binding: ItemTeamAnalysisBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: SummonerMatchRecord, maxValue: Int) {
        with(binding) {
            champion.setChampion(data.championName)
            summonerName.text = data.summonerName
            itemValue.text = data.totalDealt.toString()
            valueGraph.progress = data.totalDealt
            valueGraph.max = maxValue
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): TeamDealtViewHolder {
            return TeamDealtViewHolder(
                ItemTeamAnalysisBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}