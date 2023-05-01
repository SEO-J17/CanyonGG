package io.github.seoj17.presentation.ui.detail.analysisTab.pages.minion

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.seoj17.presentation.model.SummonerMatchRecord
import io.github.seoj17.presentation.ui.detail.analysisTab.pages.AnalysisPagerListViewHolder
import io.github.seoj17.presentation.utils.NumberFormatter
import io.github.seoj17.presentation.databinding.ItemTeamAnalysisBinding

class TeamMinionsViewHolder(
    private val binding: ItemTeamAnalysisBinding,
) : AnalysisPagerListViewHolder(binding.root) {
    override fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>) {
        with(binding) {
            data = dataSet
            itemValue.text = NumberFormatter.formatNumber(dataSet.minions)
            valueGraph.max = wholeData.maxOf { it.minions }
            valueGraph.progress = dataSet.minions
        }
    }

    companion object : CreateViewHolder {
        override operator fun invoke(parent: ViewGroup): TeamMinionsViewHolder {
            return TeamMinionsViewHolder(
                ItemTeamAnalysisBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
