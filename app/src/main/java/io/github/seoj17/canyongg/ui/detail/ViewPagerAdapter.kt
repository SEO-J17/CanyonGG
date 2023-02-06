package io.github.seoj17.canyongg.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MatchSummaryFragment()
            1 -> TeamAnalysisFragment()
            else -> MyRankFragment()
        }
    }
}