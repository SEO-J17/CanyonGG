package io.github.seoj17.presentaion.ui.detail

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.ui.detail.analysisTab.TeamAnalysisFragment
import io.github.seoj17.presentaion.ui.detail.rankTab.MyRankFragment
import io.github.seoj17.presentaion.ui.detail.summaryTab.MatchSummaryFragment

enum class DetailMatchTabs(
    @StringRes val title: Int,
    val newInstance: () -> Fragment,
) {
    SUMMARY(
        title = R.string.match_summary,
        newInstance = MatchSummaryFragment::newInstance,
    ),

    ANALYSIS(
        title = R.string.team_analysis,
        newInstance = TeamAnalysisFragment::newInstance,
    ),

    RANK(
        title = R.string.my_rank,
        newInstance = MyRankFragment::newInstance,
    ),
    ;

    companion object {
        fun getMatchTab(position: Int) = values()[position]
    }
}
