package io.github.seoj17.canyongg.ui.detail.analysisTab.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentTeamVisionScoreBinding

@AndroidEntryPoint
class TeamVisionScoreFragment : Fragment() {
    private lateinit var binding: FragmentTeamVisionScoreBinding
    private val viewModel: TeamVisionScoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTeamVisionScoreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            teamVisionScoreList.adapter = AnalysisPageListAdapter(4)
        }
    }

    companion object {
        fun newInstance(matchId: String): TeamVisionScoreFragment {
            return TeamVisionScoreFragment().apply {
                arguments = Bundle().apply {
                    putString("matchId", matchId)
                }
            }
        }
    }
}