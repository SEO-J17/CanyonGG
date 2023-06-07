package io.github.seoj17.presentaion.ui.detail.summaryTab

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentMatchSummaryBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.detail.DetailMatchFragmentDirections
import io.github.seoj17.presentaion.ui.detail.DetailMatchViewModel

@AndroidEntryPoint
class MatchSummaryFragment :
    BaseDataBindingFragment<FragmentMatchSummaryBinding, MatchSummaryViewModel>(
        FragmentMatchSummaryBinding::inflate,
    ) {
    override val viewModel: MatchSummaryViewModel by viewModels()
    private val parentViewModel: DetailMatchViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
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
