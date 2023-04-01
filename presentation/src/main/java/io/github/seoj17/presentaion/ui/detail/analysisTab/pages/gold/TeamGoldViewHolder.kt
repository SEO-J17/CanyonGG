package io.github.seoj17.presentaion.ui.detail.analysisTab.pages.gold

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.seoj17.presentaion.databinding.ItemTeamAnalysisBinding
import io.github.seoj17.presentaion.model.SummonerMatchRecord
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPagerListViewHolder
import io.github.seoj17.presentaion.utils.NumberFormatter

class TeamGoldViewHolder(
    private val binding: ItemTeamAnalysisBinding,
) : AnalysisPagerListViewHolder(binding.root) {

    override fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>) {
        with(binding) {
            data = dataSet
            itemValue.text = NumberFormatter.formatNumber(dataSet.spentGold)
            valueGraph.max = wholeData.maxOf { it.spentGold }
            valueGraph.progress = dataSet.spentGold
        }
    }

    companion object : CreateViewHolder {
        override operator fun invoke(parent: ViewGroup): TeamGoldViewHolder {
            return TeamGoldViewHolder(
                ItemTeamAnalysisBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
