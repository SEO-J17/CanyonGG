package io.github.seoj17.canyongg.ui.detail.analysisTab.pages

import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.damaged.TeamDamagedViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.dealt.TeamDealtViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.gold.TeamGoldViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.kill.TeamKillsViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.minion.TeamMinionsViewHolder
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.vision.TeamVisionScoreViewHolder

sealed class AnalysisPagerTabs(
    val viewHolder: AnalysisPagerListViewHolder.CreateViewHolder,
) {
    object Kills : AnalysisPagerTabs(TeamKillsViewHolder)

    object Dealt : AnalysisPagerTabs(TeamDealtViewHolder)

    object Damaged : AnalysisPagerTabs(TeamDamagedViewHolder)

    object SpentGold : AnalysisPagerTabs(TeamGoldViewHolder)

    object Minions : AnalysisPagerTabs(TeamMinionsViewHolder)

    object VisionScore : AnalysisPagerTabs(TeamVisionScoreViewHolder)
}
