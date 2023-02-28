package io.github.seoj17.canyongg.ui.detail.analysisTab

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class AnalysisViewPagerAdapter(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {

    private val tabs = TeamAnalysisTabs.values()

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment = tabs[position].newInstance

}