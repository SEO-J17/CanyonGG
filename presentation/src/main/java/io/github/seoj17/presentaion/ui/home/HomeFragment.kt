package io.github.seoj17.presentaion.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentHomeBinding
import io.github.seoj17.presentaion.ui.main.SharedViewModel
import io.github.seoj17.presentaion.ui.state.UiState
import io.github.seoj17.presentaion.utils.showToast
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            sharedViewModel.representativeSummoner.observe(viewLifecycleOwner) {
                viewModel.fetchInfo(it)
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.searchState.collect { state ->
                        when (state) {
                            is UiState.Empty -> { Unit }
                            is UiState.Success -> {
                                summonerTab.visibility = View.VISIBLE
                                mostChampGroup.visibility = View.VISIBLE
                                detailMyInfo.visibility = View.VISIBLE
                            }
                            is UiState.Loading -> {
                                registerUserTab.visibility = View.GONE
                                summonerTab.visibility = View.GONE
                                mostChampGroup.visibility = View.GONE
                                detailMyInfo.visibility = View.GONE
                            }
                        }
                    }
                }
            }

            registerUserTab.setClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeToRegisterSummoner())
            }

            searchSummonerTab.setClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeToSearchGraph())
            }

            summonerTab.setClickListener {
                viewModel.removeMyInfo()
                requireActivity().showToast(R.string.info_delete_toast)
            }

            summonerTab.setRefreshClickListener {
                viewModel.refreshMyInfo()
                requireActivity().showToast(R.string.infro_refresh_toast)
            }

            detailMyInfo.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToSearchGraph(
                        summonerName = viewModel.userInfo.value?.name,
                        summonerPuuid = viewModel.userInfo.value?.puuid,
                    ),
                )
            }

            bookMarkList.adapter = BookmarkListAdapter(
                { deleteName ->
                    viewModel.removeBookmark(deleteName)
                },
                { name, puuid ->
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeToSearchGraph(
                            summonerName = name,
                            summonerPuuid = puuid,
                        ),
                    )
                },
            )

            champRotationListView.adapter = RotationChampListAdapter()
        }
    }
}
