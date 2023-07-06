package io.github.seoj17.presentaion.ui.home

import android.view.View
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
            registerUserTab.visibility = if (viewModel.userInfo.value == null) {
                View.VISIBLE
            } else {
                View.GONE
            }
            registerUserTab.setClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeToRegisterSummoner())
            }

            searchSummonerTab.setClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeToSearchGraph())
            }

            summonerTab.setClickListener {
                viewModel.removeMyInfo()
                registerUserTab.visibility = View.VISIBLE
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
                { champion ->
                    TODO("클릭시 챔피언 디테일로 넘어가기")
                },
            )

            champRotationListView.adapter = RotationChampListAdapter()
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.userInfoState.collect {
                    binding.userInfoLoading.visibility = if (it) {
                        binding.registerUserTab.visibility = View.GONE
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
    }
}
