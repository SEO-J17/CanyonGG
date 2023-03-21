package io.github.seoj17.canyongg.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.FragmentSummonerRecordBinding

@AndroidEntryPoint
class SummonerRecordFragment : Fragment() {
    private lateinit var binding: FragmentSummonerRecordBinding
    private val viewModel: SummonerRecordViewModel by viewModels()

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
                    bookmarkedMessage(R.string.delete_bookmark_message)
                } else {
                    it.isSelected = true
                    viewModel.addBookmark()
                    bookmarkedMessage(R.string.add_bookmark_message)
                }
            }

            temp.setOnClickListener {
                findNavController().navigate(
                    SummonerRecordFragmentDirections.actionSearchResultToDetailMatch("", ""),
                )
            }
        }
    }

    private fun bookmarkedMessage(message: Int) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT,
        ).show()
    }
}
