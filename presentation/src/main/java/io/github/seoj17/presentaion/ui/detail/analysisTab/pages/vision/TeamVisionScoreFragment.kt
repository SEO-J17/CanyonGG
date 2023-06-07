package io.github.seoj17.presentaion.ui.detail.analysisTab.pages.vision

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentTeamVisionScoreBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.detail.analysisTab.TeamAnalysisViewModel
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPagerTabs

@AndroidEntryPoint
class TeamVisionScoreFragment :
    BaseDataBindingFragment<FragmentTeamVisionScoreBinding, TeamVisionScoreViewModel>(
        FragmentTeamVisionScoreBinding::inflate,
    ) {
    override val viewModel: TeamVisionScoreViewModel by viewModels()
    private val parentViewModel: TeamAnalysisViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
            teamVisionScoreList.adapter = AnalysisPageListAdapter(AnalysisPagerTabs.VisionScore)

            parentViewModel.matchId.value?.let { viewModel.setMatchId(it) }
        }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = TeamVisionScoreFragment()
    }
}
