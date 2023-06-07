package io.github.seoj17.presentaion.ui.detail.analysisTab.pages.damaged

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentTeamDamagedBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.detail.analysisTab.TeamAnalysisViewModel
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPagerTabs

@AndroidEntryPoint
class TeamDamagedFragment : BaseDataBindingFragment<FragmentTeamDamagedBinding, TeamDamagedViewModel>(
    FragmentTeamDamagedBinding::inflate,
) {
    override val viewModel: TeamDamagedViewModel by viewModels()
    override fun viewModelVariableId(): Int = BR.vm

    private val parentViewModel: TeamAnalysisViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun bindLayout() {
        with(binding) {
            teamDamagedList.adapter = AnalysisPageListAdapter(AnalysisPagerTabs.Damaged)
        }
        parentViewModel.matchId.value?.let { viewModel.setMatchId(it) }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = TeamDamagedFragment()
    }
}
