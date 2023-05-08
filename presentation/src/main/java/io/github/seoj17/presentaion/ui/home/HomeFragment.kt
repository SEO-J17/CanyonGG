package io.github.seoj17.presentaion.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentHomeBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.utils.showToast

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModels()
    override fun bindLayout() {
        with(binding) {
            vm = viewModel

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
                    HomeFragmentDirections.actionHomeToSearchGraph(
                        summonerName = name,
                        summonerPuuid = puuid,
                    ),
                )
            }

            champRotationListView.adapter = RotationChampListAdapter()
        }
    }

    override fun observeViewModel() = Unit
}
