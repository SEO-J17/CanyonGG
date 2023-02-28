package io.github.seoj17.canyongg.ui.detail.analysisTab.pages

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.damaged.TeamDamagedViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.dealt.TeamDealtViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.gold.TeamGoldViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.kill.TeamKillsViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.minion.TeamMinionsViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.vision.TeamVisionScoreViewHolder
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

class AnalysisPageListAdapter(
    private val pageType: Int,
) : ListAdapter<SummonerMatchRecord, AnalysisPagerListViewHolder>(SummonerMatchRecord.diffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AnalysisPagerListViewHolder {
        return when (pageType) {
            AnalysisPagerTabs.KILLS.type -> TeamKillsViewHolder(parent)
            AnalysisPagerTabs.DEALT.type -> TeamDealtViewHolder(parent)
            AnalysisPagerTabs.DAMAGED.type -> TeamGoldViewHolder(parent)
            AnalysisPagerTabs.SPENT_GOLD.type -> TeamDamagedViewHolder(parent)
            AnalysisPagerTabs.MINIONS.type -> TeamMinionsViewHolder(parent)
            AnalysisPagerTabs.VISION_SCORE.type -> TeamVisionScoreViewHolder(parent)
            else -> throw Exception("no page")
        }
    }

    override fun onBindViewHolder(holder: AnalysisPagerListViewHolder, position: Int) {
        val dataSet = getItem(position) ?: return
        with(holder) {
            bind(dataSet, currentList)
        }
    }
}