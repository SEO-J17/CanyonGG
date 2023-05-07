package io.github.seoj17.presentaion.ui.detail.analysisTab

import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentTeamAnalysisBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.ui.detail.DetailMatchViewModel

@AndroidEntryPoint
class TeamAnalysisFragment : BaseFragment<FragmentTeamAnalysisBinding, TeamAnalysisViewModel>(
    FragmentTeamAnalysisBinding::inflate,
) {
    override val viewModel: TeamAnalysisViewModel by viewModels()
    private val parentViewModel: DetailMatchViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun bindLayout() {
        with(binding) {
            analysisTabPager.adapter =
                AnalysisViewPagerAdapter(this@TeamAnalysisFragment)

            TabLayoutMediator(analysisTabLayout, analysisTabPager) { tab, position ->
                tab.setText(TeamAnalysisTabs(position).title)
            }.attach()

            viewModel.setMatchId(parentViewModel.matchId)
        }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = TeamAnalysisFragment()
    }
}
