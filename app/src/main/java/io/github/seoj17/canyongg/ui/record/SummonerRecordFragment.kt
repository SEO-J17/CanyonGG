package io.github.seoj17.canyongg.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.FragmentSummonerRecordBinding
import io.github.seoj17.canyongg.utils.observeEvent

@AndroidEntryPoint
class SummonerRecordFragment : Fragment() {
    private lateinit var binding: FragmentSummonerRecordBinding
    private val viewModel: SummonerRecordViewModel by viewModels()
    private lateinit var navigator: NavController

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
        navigator = Navigation.findNavController(view)

        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            summonerHistoryView.adapter = RecordListAdapter { matchId, puuid ->
                navigator.navigate(
                    SummonerRecordFragmentDirections.actionSearchResultToDetailMatch(
                        matchId,
                        puuid,
                    )
                )
            }

            viewModel.addBookmarkEvent.observeEvent(viewLifecycleOwner) {
                setBookmarkState(
                    getString(
                        R.string.add_bookmark_message
                    ),
                    View.VISIBLE,
                    View.GONE
                )
            }

            viewModel.deleteBookmarkEvent.observeEvent(viewLifecycleOwner) {
                setBookmarkState(
                    getString(R.string.delete_bookmark_message),
                    View.GONE,
                    View.VISIBLE,
                )
            }

            temp.setOnClickListener {
                navigator.navigate(
                    SummonerRecordFragmentDirections.actionSearchResultToDetailMatch("", "")
                )
            }
        }
    }

    private fun setBookmarkState(message: String, state1: Int, state2: Int) {
        Toast.makeText(
            this@SummonerRecordFragment.context, message, Toast.LENGTH_SHORT
        ).show()
        binding.summonerAddFinishBookMark.visibility = state1
        binding.summonerEmptyBookMark.visibility = state2
    }
}