package io.github.seoj17.presentaion.ui.champion

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentChampionBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment

@AndroidEntryPoint
class ChampionFragment : BaseFragment<FragmentChampionBinding, ChampionViewModel>(
    FragmentChampionBinding::inflate,
) {
    override val viewModel: ChampionViewModel by viewModels()

    override fun bindLayout() {
        with(binding) {
            vm = viewModel
            championList.adapter = ChampionListAdapter { TODO("챔피언 클릭 디테일 구현") }
        }
    }

    override fun observeViewModel() = Unit
}
