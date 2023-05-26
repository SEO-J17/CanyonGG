package io.github.seoj17.presentaion.ui.champion

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentChampionBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.utils.showToast

@AndroidEntryPoint
class ChampionFragment : BaseFragment<FragmentChampionBinding, ChampionViewModel>(
    FragmentChampionBinding::inflate,
) {
    override val viewModel: ChampionViewModel by viewModels()

    override fun bindLayout() {
        with(binding) {
            vm = viewModel
            championList.adapter = ChampionListAdapter(
                { champKey ->
                    findNavController().navigate(
                        ChampionFragmentDirections.actionChampionsToChampionDetailFragment(champKey),
                    )
                },
                { addBookmarkChamp ->
                    viewModel.addBookmark(addBookmarkChamp)
                    requireActivity().showToast(R.string.add_bookmark_message)
                },
                { deleteBookmarkChamp ->
                    viewModel.deleteBookmark(deleteBookmarkChamp)
                    requireActivity().showToast(R.string.delete_bookmark_message)
                },
            )
        }
    }

    override fun observeViewModel() {
    }
}
