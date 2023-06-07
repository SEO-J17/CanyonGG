package io.github.seoj17.presentaion.ui.champion.detail

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentChampionDetailBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment

@AndroidEntryPoint
class ChampionDetailFragment :
    BaseDataBindingFragment<FragmentChampionDetailBinding, ChampionDetailViewModel>(
        FragmentChampionDetailBinding::inflate,
    ) {
    override val viewModel: ChampionDetailViewModel by viewModels()
    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() = Unit

    override fun observeViewModel() = Unit
}
