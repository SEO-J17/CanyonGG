package io.github.seoj17.presentaion.ui.detail.analysisTab.pages.dealt

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentTeamDealtBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.ui.detail.analysisTab.TeamAnalysisViewModel
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPagerTabs

@AndroidEntryPoint
class TeamDealtFragment : BaseFragment<FragmentTeamDealtBinding, TeamDealtViewModel>(
    FragmentTeamDealtBinding::inflate,
) {
    override val viewModel: TeamDealtViewModel by viewModels()
    private val parentViewModel: TeamAnalysisViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun bindLayout() {
        with(binding) {
            vm = viewModel
            teamDealtList.adapter = AnalysisPageListAdapter(AnalysisPagerTabs.Dealt)

            parentViewModel.matchId.value?.let { viewModel.setMatchId(it) }
        }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = TeamDealtFragment()
    }
}
