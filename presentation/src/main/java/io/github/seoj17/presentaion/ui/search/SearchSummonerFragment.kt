package io.github.seoj17.presentaion.ui.search

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentSearchSummonerBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.dialog.NotFoundUserDialogFragment

@AndroidEntryPoint
class SearchSummonerFragment :
    BaseDataBindingFragment<FragmentSearchSummonerBinding, SearchSummonerViewModel>(
        FragmentSearchSummonerBinding::inflate,
    ) {

    override val viewModel: SearchSummonerViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
            recentSummonerListView.adapter = SearchSummonerListAdapter(
                { viewModel.deleteRecentSummoner(it) },
                { name, puuid ->
                    moveToSearchResult(name, puuid)
                },
            )
        }
    }

    override fun observeViewModel() {
        viewModel.searchSummoner.observe(viewLifecycleOwner) {
            it?.let { summoner ->
                moveToSearchResult(summoner.name, summoner.puuid)
            } ?: NotFoundUserDialogFragment().show(parentFragmentManager, null)
        }
    }

    private fun moveToSearchResult(name: String, puuid: String) {
        findNavController().navigate(
            SearchSummonerFragmentDirections.actionSearchSummonerToSearchResultGraph(
                name,
                puuid,
            ),
        )
    }
}
