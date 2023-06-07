package io.github.seoj17.presentaion.ui.detail.rankTab

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentMyRankBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.detail.DetailMatchViewModel

@AndroidEntryPoint
class MyRankFragment : BaseDataBindingFragment<FragmentMyRankBinding, MyRankViewModel>(
    FragmentMyRankBinding::inflate,
) {
    override val viewModel: MyRankViewModel by viewModels()
    private val parentViewModel: DetailMatchViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
            viewModel.setMatchId(parentViewModel.matchId)
            viewModel.setSummonerPuuid(parentViewModel.puuId)
        }
    }

    override fun observeViewModel() = Unit

    companion object {
        fun newInstance() = MyRankFragment()
    }
}
