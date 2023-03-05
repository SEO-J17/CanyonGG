package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.dealt

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.seoj17.canyongg.databinding.ItemTeamAnalysisBinding
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.AnalysisPagerListViewHolder
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.utils.NumberFormatter

class TeamDealtViewHolder(
    private val binding: ItemTeamAnalysisBinding,
) : AnalysisPagerListViewHolder(binding.root) {

    override fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>) {
        with(binding) {
            data = dataSet
            itemValue.text = NumberFormatter.formatNumber(dataSet.totalDealt)
            valueGraph.progress = dataSet.totalDealt
            valueGraph.max = wholeData.maxOf { it.totalDealt }
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