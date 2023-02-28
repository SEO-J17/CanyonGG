package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.kill

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.seoj17.canyongg.databinding.ItemTeamAnalysisBinding
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.AnalysisPagerListViewHolder
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

class TeamKillsViewHolder(
    private val binding: ItemTeamAnalysisBinding,
) : AnalysisPagerListViewHolder(binding.root) {

    override fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>) {
        with(binding) {
            data = dataSet
            itemValue.text = dataSet.kill.toString()
            valueGraph.progress = dataSet.kill
            valueGraph.max = wholeData.maxOf { it.kill }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): TeamKillsViewHolder {
            return TeamKillsViewHolder(
                ItemTeamAnalysisBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}