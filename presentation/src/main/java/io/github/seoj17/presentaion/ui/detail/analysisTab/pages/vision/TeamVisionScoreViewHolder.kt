package io.github.seoj17.presentaion.ui.detail.analysisTab.pages.vision

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.seoj17.presentaion.databinding.ItemTeamAnalysisBinding
import io.github.seoj17.presentaion.model.SummonerMatchRecord
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPagerListViewHolder
import io.github.seoj17.presentaion.utils.NumberFormatter

class TeamVisionScoreViewHolder(
    private val binding: ItemTeamAnalysisBinding,
) : AnalysisPagerListViewHolder(binding.root) {

    override fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>) {
        with(binding) {
            data = dataSet
            itemValue.text = NumberFormatter.formatNumber(dataSet.visionScore)
            valueGraph.max = wholeData.maxOf { it.visionScore }
            valueGraph.progress = dataSet.visionScore
        }
    }

    companion object : CreateViewHolder {
        override operator fun invoke(parent: ViewGroup): TeamVisionScoreViewHolder {
            return TeamVisionScoreViewHolder(
                ItemTeamAnalysisBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
