package io.github.seoj17.presentaion.ui.champion.detail

import androidx.fragment.app.viewModels
import io.github.seoj17.presentaion.databinding.FragmentChampionDetailBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment

class ChampionDetailFragment :
    BaseFragment<FragmentChampionDetailBinding, ChampionDetailViewModel>(
        FragmentChampionDetailBinding::inflate,
    ) {
    override val viewModel: ChampionDetailViewModel by viewModels()

    override fun bindLayout() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun observeViewModel() {
    }
}
