package io.github.seoj17.presentaion.ui.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentHomeBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.utils.showToast
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment :
    BaseDataBindingFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
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
                    HomeFragmentDirections.actionHomeToSearchResultGraph(
                        summonerName = viewModel.userInfo.value?.name,
                        summonerPuuid = viewModel.userInfo.value?.puuid,
                    ),
                )
            }

            championTab.setClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToChampionGraph(),
                )
            }

            bookMarkList.adapter = BookmarkListAdapter(
                { deleteName ->
                    viewModel.removeBookmark(deleteName)
                },
            ) { name, puuid ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToSearchResultGraph(
                        summonerName = name,
                        summonerPuuid = puuid,
                    ),
                )
            }

            championBookMarkList.adapter = BookmarkChampionListAdapter(
                { deleteChamp ->
                    viewModel.removeChampionBookmark(deleteChamp)
                },
                { championKey ->
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeToChampionDetail(
                            champKey = championKey,
                        ),
                    )
                },
            )

            champRotationListView.adapter = RotationChampListAdapter { champKey ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToChampionDetail(
                        champKey = champKey,
                    ),
                )
            }
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.userInfoState.collect {
                    tabRefreshVisibility(it)
                }
            }
        }
    }

    private fun tabRefreshVisibility(isRefresh: Boolean) {
        with(binding) {
            summonerTab.isVisible = !isRefresh
            mostChampTab.isVisible = !isRefresh
            detailMyInfo.isVisible = !isRefresh
        }
    }
}
