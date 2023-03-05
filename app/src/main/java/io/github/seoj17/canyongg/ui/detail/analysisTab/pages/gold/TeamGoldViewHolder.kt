package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.gold

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.seoj17.canyongg.databinding.ItemTeamAnalysisBinding
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.AnalysisPagerListViewHolder
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.utils.NumberFormatter

class TeamGoldViewHolder(
    private val binding: ItemTeamAnalysisBinding,
) : AnalysisPagerListViewHolder(binding.root) {

    override fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>) {
        with(binding) {
            data = dataSet
            itemValue.text = NumberFormatter.formatNumber(dataSet.spentGold)
            valueGraph.progress = dataSet.spentGold
            valueGraph.max = wholeData.maxOf { it.spentGold }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): TeamGoldViewHolder {
            return TeamGoldViewHolder(
                ItemTeamAnalysisBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}