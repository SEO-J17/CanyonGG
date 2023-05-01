package io.github.seoj17.presentation.ui.detail.analysisTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentation.ui.detail.DetailMatchViewModel
import io.github.seoj17.presentation.databinding.FragmentTeamAnalysisBinding

@AndroidEntryPoint
class TeamAnalysisFragment : Fragment() {
    private lateinit var binding: FragmentTeamAnalysisBinding
    private val viewModel: TeamAnalysisViewModel by viewModels()
    private val parentViewModel: DetailMatchViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTeamAnalysisBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            analysisTabPager.adapter =
                AnalysisViewPagerAdapter(this@TeamAnalysisFragment)

            TabLayoutMediator(analysisTabLayout, analysisTabPager) { tab, position ->
                tab.setText(TeamAnalysisTabs(position).title)
            }.attach()

            viewModel.setMatchId(parentViewModel.matchId)
        }
    }

    companion object {
        fun newInstance() = TeamAnalysisFragment()
    }
}
