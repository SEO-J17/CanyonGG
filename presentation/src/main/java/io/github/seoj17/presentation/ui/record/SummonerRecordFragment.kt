package io.github.seoj17.presentation.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentation.ui.main.SharedViewModel
import io.github.seoj17.presentation.utils.showToast
import io.github.seoj17.presentation.R
import io.github.seoj17.presentation.databinding.FragmentSummonerRecordBinding

@AndroidEntryPoint
class SummonerRecordFragment : Fragment() {
    private lateinit var binding: FragmentSummonerRecordBinding
    private val viewModel: SummonerRecordViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSummonerRecordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            sharedViewModel.searchSummoner.observe(viewLifecycleOwner) {
                viewModel.fetchSummonerInfo(it)
            }

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
}
