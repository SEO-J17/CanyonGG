package io.github.seoj17.canyongg.ui.detail.analysisTab.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentTeamGoldBinding

@AndroidEntryPoint
class TeamGoldFragment : Fragment() {
    private lateinit var binding: FragmentTeamGoldBinding
    private val viewModel: TeamGoldViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
            teamGoldList.adapter = AnalysisPageListAdapter(2)
        }
    }
    companion object {
        fun newInstance(matchId: String): TeamGoldFragment {
            return TeamGoldFragment().apply {
                arguments = Bundle().apply {
                    putString("matchId", matchId)
                }
            }
        }
    }
}