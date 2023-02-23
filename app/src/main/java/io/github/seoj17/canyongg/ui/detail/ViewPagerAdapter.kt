package io.github.seoj17.canyongg.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.github.seoj17.canyongg.ui.detail.analysisTab.TeamAnalysisFragment
import io.github.seoj17.canyongg.ui.detail.rankTab.MyRankFragment
import io.github.seoj17.canyongg.ui.detail.summaryTab.MatchSummaryFragment

class ViewPagerAdapter(
    fragment: Fragment,
    matchId: String,
    puuid: String,
) : FragmentStateAdapter(fragment) {

    private val tabs: List<Fragment> = listOf(
        MatchSummaryFragment.newInstance(matchId, puuid),
        TeamAnalysisFragment.newInstance(matchId, puuid),
        MyRankFragment.newInstance(matchId, puuid)
    )

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        return tabs[position]
    }
}