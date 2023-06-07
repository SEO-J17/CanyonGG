package io.github.seoj17.presentaion.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentHomeBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.utils.showToast

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

    override fun observeViewModel() = Unit
}
