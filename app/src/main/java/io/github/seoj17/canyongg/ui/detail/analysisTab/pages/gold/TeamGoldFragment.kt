package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.gold

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentTeamGoldBinding
import io.github.seoj17.canyongg.ui.detail.analysisTab.TeamAnalysisViewModel
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.AnalysisPagerTabs

@AndroidEntryPoint
class TeamGoldFragment : Fragment() {
    private lateinit var binding: FragmentTeamGoldBinding
    private val viewModel: TeamGoldViewModel by viewModels()
    private val parentViewModel: TeamAnalysisViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTeamGoldBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            teamGoldList.adapter = AnalysisPageListAdapter(AnalysisPagerTabs.SPENT_GOLD.type)

            parentViewModel.matchId.value?.let { viewModel.setMatchId(it) }
            viewModel.fetch()
        }
    }

    companion object {
        fun newInstance() = TeamGoldFragment()
    }
}