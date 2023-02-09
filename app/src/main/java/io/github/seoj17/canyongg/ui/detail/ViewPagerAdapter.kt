package io.github.seoj17.canyongg.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val tabs: List<Fragment> = listOf(
        MatchSummaryFragment.newInstance(),
        TeamAnalysisFragment.newInstance(),
        MyRankFragment.newInstance()
    )

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        return tabs[position]
    }
}