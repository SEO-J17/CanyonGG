package io.github.seoj17.canyongg.ui.detail.summaryTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentMatchSummaryBinding
import io.github.seoj17.canyongg.ui.detail.DetailMatchFragmentDirections
import io.github.seoj17.canyongg.ui.detail.DetailMatchViewModel

@AndroidEntryPoint
class MatchSummaryFragment : Fragment() {
    private lateinit var binding: FragmentMatchSummaryBinding
    private val viewModel: MatchSummaryViewModel by viewModels()
    private val parentViewModel: DetailMatchViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMatchSummaryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel

            winList.adapter = WinParticipantsListAdapter { name, puuid ->
                findNavController().navigate(
                    DetailMatchFragmentDirections.actionDetailMatchToSearchResult(
                        name,
                        puuid,
                    )
                )
            }

            loseList.adapter = LoseParticipantsListAdapter { name, puuid ->
                findNavController().navigate(
                    DetailMatchFragmentDirections.actionDetailMatchToSearchResult(
                        name,
                        puuid,
                    )
                )
            }
            viewModel.setMatchId(parentViewModel.matchId)
            viewModel.fetch()
        }
    }

    companion object {
        fun newInstance() = MatchSummaryFragment()
    }
}