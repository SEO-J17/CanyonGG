package io.github.seoj17.presentaion.ui.champion

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentChampionBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.utils.showToast
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChampionFragment : BaseDataBindingFragment<FragmentChampionBinding, ChampionViewModel>(
    FragmentChampionBinding::inflate,
) {
    override val viewModel: ChampionViewModel by viewModels()
    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
            championList.itemAnimator = null
            championList.adapter = ChampionListAdapter(
                { champKey ->
                    findNavController().navigate(
                        ChampionFragmentDirections.actionChampionsToChampionDetailFragment(champKey),
                    )
                },
                { champion ->
                    viewModel.onBookmarkClick(champion)
                },
            )
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.bookmarkEvent.collect {
                    if (it) {
                        requireActivity().showToast(R.string.add_bookmark_message)
                    } else {
                        requireActivity().showToast(R.string.delete_bookmark_message)
                    }
                }
            }
        }
    }
}
