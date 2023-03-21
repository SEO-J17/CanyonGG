package io.github.seoj17.canyongg.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentSearchSummonerBinding
import io.github.seoj17.canyongg.ui.dialog.NotFoundUserDialogFragment
import io.github.seoj17.canyongg.utils.observeEvent

@AndroidEntryPoint
class SearchSummonerFragment : Fragment() {
    private lateinit var binding: FragmentSearchSummonerBinding
    private val viewModel: SearchSummonerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSearchSummonerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
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

            viewModel.searchResult.observe(viewLifecycleOwner) { summoner ->
                summoner?.let {
                    findNavController().navigate(
                        SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                            summoner.name,
                            summoner.puuid,
                        ),
                    )
                }
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

            viewModel.errorEvent.observeEvent(viewLifecycleOwner) {
                NotFoundUserDialogFragment().show(childFragmentManager, null)
            }
            // 임시버튼. 화면 이동하게함
            temp.setOnClickListener {
                findNavController().navigate(
                    SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                        "",
                        "",
                    ),
                )
            }
        }
    }
}
