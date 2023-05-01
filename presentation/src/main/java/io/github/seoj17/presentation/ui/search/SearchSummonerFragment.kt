package io.github.seoj17.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentation.ui.dialog.NotFoundUserDialogFragment
import io.github.seoj17.presentation.ui.main.SharedViewModel
import io.github.seoj17.presentation.databinding.FragmentSearchSummonerBinding

@AndroidEntryPoint
class SearchSummonerFragment : Fragment() {
    private lateinit var binding: FragmentSearchSummonerBinding
    private val viewModel: SearchSummonerViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

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

            viewModel.summonerInfo.observe(viewLifecycleOwner) { summoner ->
                summoner?.let {
                    sharedViewModel.fetchSearchSummoner(it)
                    findNavController().navigate(
                        SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult(
                            summoner.name,
                            summoner.puuid,
                        ),
                    )
                } ?: NotFoundUserDialogFragment().show(childFragmentManager, null)
            }
        }
    }
}
