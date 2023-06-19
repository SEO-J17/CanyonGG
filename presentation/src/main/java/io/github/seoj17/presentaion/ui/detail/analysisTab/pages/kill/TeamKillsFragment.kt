package io.github.seoj17.presentaion.ui.detail.analysisTab.pages.kill

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentTeamKillsBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.detail.analysisTab.TeamAnalysisViewModel
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPagerTabs

@AndroidEntryPoint
class TeamKillsFragment : BaseDataBindingFragment<FragmentTeamKillsBinding, TeamKillsViewModel>(
    FragmentTeamKillsBinding::inflate,
) {
    override val viewModel: TeamKillsViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    private val parentViewModel: TeamAnalysisViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun bindLayout() {
        with(binding) {
            teamKillsList.adapter = AnalysisPageListAdapter(AnalysisPagerTabs.Kills)

            parentViewModel.matchId.value?.let { viewModel.setMatchId(it) }
        }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = TeamKillsFragment()
    }
}
