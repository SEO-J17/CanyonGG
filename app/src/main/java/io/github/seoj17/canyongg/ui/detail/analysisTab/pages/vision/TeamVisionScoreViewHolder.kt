package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.vision

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.seoj17.canyongg.databinding.ItemTeamAnalysisBinding
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.AnalysisPagerListViewHolder
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.utils.NumberFormatter

class TeamVisionScoreViewHolder(
    private val binding: ItemTeamAnalysisBinding,
) : AnalysisPagerListViewHolder(binding.root) {

    override fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>) {
        with(binding) {
            data = dataSet
            itemValue.text = NumberFormatter.formatNumber(dataSet.visionScore)
            valueGraph.progress = dataSet.visionScore
            valueGraph.max = wholeData.maxOf { it.visionScore }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): TeamVisionScoreViewHolder {
            return TeamVisionScoreViewHolder(
                ItemTeamAnalysisBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}