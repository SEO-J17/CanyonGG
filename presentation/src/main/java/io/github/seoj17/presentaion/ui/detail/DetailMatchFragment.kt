package io.github.seoj17.presentaion.ui.detail

import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentDetailMatchBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment

@AndroidEntryPoint
class DetailMatchFragment : BaseFragment<FragmentDetailMatchBinding, DetailMatchViewModel>(
    FragmentDetailMatchBinding::inflate,
) {
    override val viewModel: DetailMatchViewModel by viewModels()

    override fun bindLayout() {
        with(binding) {
            toolbar.setOnClickListener {
                requireActivity().onBackPressed()
            }

            tabPager.adapter = DetailMatchViewPagerAdapter(this@DetailMatchFragment)
            TabLayoutMediator(tabLayout, tabPager) { tab, position ->
                tab.setText(DetailMatchTabs.getMatchTab(position).title)
            }.attach()
        }
    }

    override fun observeViewModel() = Unit
}
