package io.github.seoj17.canyongg.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentSearchSummonerBinding
import io.github.seoj17.canyongg.ui.dialog.NotFoundUserDialogFragment
import io.github.seoj17.canyongg.utils.observeEvent

@AndroidEntryPoint
class SearchSummonerFragment : Fragment() {
    private lateinit var binding: FragmentSearchSummonerBinding
    private val viewModel: SearchSummonerViewModel by viewModels()
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSearchSummonerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            recentSummonerListView.adapter =
                SearchSummonerListAdapter(
                    { viewModel.deleteRecentSummoner(it) }
                ) { name, puuid ->
                    navigator.navigate(
                        SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                            name,
                            puuid,
                        )
                    )
                }

            viewModel.searchResult.observe(viewLifecycleOwner) { summoner ->
                summoner?.let {
                    navigator.navigate(
                        SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                            summoner.name,
                            summoner.puuid,
                        )
                    )
                }
            }

            if (viewModel.isSetArguments()) {
                navigator.navigate(
                    SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                        viewModel.summonerName,
                        viewModel.summonerPuuid,
                    )
                )
            }

            viewModel.errorEvent.observeEvent(viewLifecycleOwner) {
                NotFoundUserDialogFragment().show(childFragmentManager, null)
            }
            //임시버튼. 화면 이동하게함
            temp.setOnClickListener {
                navigator.navigate(
                    SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                        "", ""
                    )
                )
            }
        }
    }
}