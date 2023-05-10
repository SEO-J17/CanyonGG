package io.github.seoj17.presentaion.ui.champion

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
            championList.adapter = ChampionListAdapter { champKey ->
                findNavController().navigate(
                    ChampionFragmentDirections.actionChampionsToChampionDetailFragment(champKey),
                )
            }
        }
    }

    override fun observeViewModel() = Unit
}
