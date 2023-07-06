package io.github.seoj17.presentaion.ui.search

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentSearchSummonerBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.dialog.NotFoundUserDialogFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchSummonerFragment :
    BaseDataBindingFragment<FragmentSearchSummonerBinding, SearchSummonerViewModel>(
        FragmentSearchSummonerBinding::inflate,
    ) {

    override val viewModel: SearchSummonerViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
            recentSummonerListView.adapter =
                SearchSummonerListAdapter(
                    { viewModel.deleteRecentSummoner(it) },
                ) { name, puuid ->
                    findNavController().navigate(
                        SearchSummonerFragmentDirections.actionSearchSummonerToSearchResultGraph(
                            name,
                            puuid,
                        ),
                    )
                }

            searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchResult(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    override fun observeViewModel() = Unit

    private fun searchResult(name: String?) {
        viewLifecycleOwner
            .lifecycleScope
            .launch {
                viewModel
                    .searchSummoner(name.toString())
                    ?.let { summoner ->
                        findNavController().navigate(
                            SearchSummonerFragmentDirections.actionSearchSummonerToSearchResultGraph(
                                summoner.name,
                                summoner.puuid,
                            ),
                        )
                    }
                    ?: NotFoundUserDialogFragment().show(
                        parentFragmentManager,
                        null,
                    )
            }
    }
}
