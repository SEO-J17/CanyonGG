package io.github.seoj17.presentaion.ui.record

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentSummonerRecordBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.utils.showToast

@AndroidEntryPoint
class SummonerRecordFragment :
    BaseDataBindingFragment<FragmentSummonerRecordBinding, SummonerRecordViewModel>(
        FragmentSummonerRecordBinding::inflate,
    ) {
    override val viewModel: SummonerRecordViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
            summonerHistoryView.adapter = RecordListAdapter { matchId, puuid ->
                findNavController().navigate(
                    SummonerRecordFragmentDirections.actionSearchResultToDetailMatch(
                        matchId,
                        puuid,
                    ),
                )
            }

            summonerBookMark.setOnClickListener {
                if (it.isSelected) {
                    it.isSelected = false
                    viewModel.deleteBookmark()
                    requireActivity().showToast(R.string.delete_bookmark_message)
                } else {
                    it.isSelected = true
                    viewModel.addBookmark()
                    requireActivity().showToast(R.string.add_bookmark_message)
                }
            }
        }
    }

    override fun observeViewModel() = Unit
}
