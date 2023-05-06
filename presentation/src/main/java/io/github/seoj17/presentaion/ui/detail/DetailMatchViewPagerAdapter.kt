package io.github.seoj17.presentaion.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailMatchViewPagerAdapter(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {

    private val tabs = DetailMatchTabs.values()

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int) = tabs[position].newInstance()
}
