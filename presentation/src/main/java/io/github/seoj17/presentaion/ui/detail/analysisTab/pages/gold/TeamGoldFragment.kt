package io.github.seoj17.presentaion.ui.detail.analysisTab.pages.gold

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentTeamGoldBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.detail.analysisTab.TeamAnalysisViewModel
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPagerTabs

@AndroidEntryPoint
class TeamGoldFragment : BaseDataBindingFragment<FragmentTeamGoldBinding, TeamGoldViewModel>(
    FragmentTeamGoldBinding::inflate,
) {
    override val viewModel: TeamGoldViewModel by viewModels()
    private val parentViewModel: TeamAnalysisViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
            teamGoldList.adapter = AnalysisPageListAdapter(AnalysisPagerTabs.SpentGold)

            parentViewModel.matchId.value?.let { viewModel.setMatchId(it) }
        }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = TeamGoldFragment()
    }
}
