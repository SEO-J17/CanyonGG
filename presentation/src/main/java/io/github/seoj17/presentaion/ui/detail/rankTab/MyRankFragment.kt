package io.github.seoj17.presentaion.ui.detail.rankTab

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentMyRankBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.ui.detail.DetailMatchViewModel

@AndroidEntryPoint
class MyRankFragment : BaseFragment<FragmentMyRankBinding, MyRankViewModel>(
    FragmentMyRankBinding::inflate,
) {
    override val viewModel: MyRankViewModel by viewModels()
    private val parentViewModel: DetailMatchViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun bindLayout() {
        with(binding) {
            vm = viewModel

            viewModel.setMatchId(parentViewModel.matchId)
            viewModel.setSummonerPuuid(parentViewModel.puuId)
        }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = MyRankFragment()
    }
}
