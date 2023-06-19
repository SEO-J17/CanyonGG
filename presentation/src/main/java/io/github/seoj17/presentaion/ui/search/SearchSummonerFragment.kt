package io.github.seoj17.presentaion.ui.search

import androidx.appcompat.widget.SearchView
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
            recentSummonerListView.adapter =
                SearchSummonerListAdapter(
                    { viewModel.deleteRecentSummoner(it) },
                ) { name, puuid ->
                    findNavController().navigate(
                        SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                            name,
                            puuid,
                        ),
                    )
                }

            searchBar.isSubmitButtonEnabled = true
            searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.validSearch(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    override fun observeViewModel() {
        viewModel.searchResult.observe(viewLifecycleOwner) { summoner ->
            summoner?.let {
                findNavController().navigate(
                    SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                        summoner.name,
                        summoner.puuid,
                    ),
                )
            } ?: NotFoundUserDialogFragment().show(childFragmentManager, null)
        }

        // 홈 화면에서 자세히 보기 버튼을 눌렀을 때 바로 검색 결과 화면이 보이도록 하는 함수
        if (viewModel.isClickDetailInfo()) {
            findNavController().navigate(
                SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                    viewModel.summonerName,
                    viewModel.summonerPuuid,
                ),
            )
        }
    }
}
