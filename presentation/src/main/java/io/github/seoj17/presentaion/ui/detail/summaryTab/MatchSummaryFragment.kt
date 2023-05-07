package io.github.seoj17.presentaion.ui.detail.summaryTab

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentMatchSummaryBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.ui.detail.DetailMatchFragmentDirections
import io.github.seoj17.presentaion.ui.detail.DetailMatchViewModel

@AndroidEntryPoint
class MatchSummaryFragment : BaseFragment<FragmentMatchSummaryBinding, MatchSummaryViewModel>(
    FragmentMatchSummaryBinding::inflate,
) {
    override val viewModel: MatchSummaryViewModel by viewModels()
    private val parentViewModel: DetailMatchViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun bindLayout() {
        with(binding) {
            vm = viewModel

            winList.adapter = WinParticipantsListAdapter { name, puuid ->
                findNavController().navigate(
                    DetailMatchFragmentDirections.actionDetailMatchToSearchResult(
                        name,
                        puuid,
                    ),
                )
            }

            loseList.adapter = LoseParticipantsListAdapter { name, puuid ->
                findNavController().navigate(
                    DetailMatchFragmentDirections.actionDetailMatchToSearchResult(
                        name,
                        puuid,
                    ),
                )
            }
            viewModel.setMatchId(parentViewModel.matchId)
        }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = MatchSummaryFragment()
    }
}
