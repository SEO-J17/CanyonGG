package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.minion

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.seoj17.canyongg.databinding.ItemTeamAnalysisBinding
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.AnalysisPagerListViewHolder
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

class TeamMinionsViewHolder(
    private val binding: ItemTeamAnalysisBinding,
) : AnalysisPagerListViewHolder(binding.root) {
    override fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>) {

        with(binding) {
            data = dataSet
            itemValue.text = dataSet.minions.toString()
            valueGraph.progress = dataSet.minions
            valueGraph.max = wholeData.maxOf { it.minions }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): TeamMinionsViewHolder {
            return TeamMinionsViewHolder(
                ItemTeamAnalysisBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}