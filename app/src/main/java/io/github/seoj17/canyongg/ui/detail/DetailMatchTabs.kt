package io.github.seoj17.canyongg.ui.detail

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.ui.detail.analysisTab.TeamAnalysisFragment
import io.github.seoj17.canyongg.ui.detail.rankTab.MyRankFragment
import io.github.seoj17.canyongg.ui.detail.summaryTab.MatchSummaryFragment

enum class DetailMatchTabs(
    @StringRes val title: Int,
    val newInstance: Fragment,
    val position: Int,
) {
    SUMMARY(
        title = R.string.match_summary,
        newInstance = MatchSummaryFragment.newInstance(),
        position = 0,
    ),

    ANALYSIS(
        title = R.string.team_analysis,
        newInstance = TeamAnalysisFragment.newInstance(),
        position = 1,
    ),

    RANK(
        title = R.string.my_rank,
        newInstance = MyRankFragment.newInstance(),
        position = 2,
    ),
}