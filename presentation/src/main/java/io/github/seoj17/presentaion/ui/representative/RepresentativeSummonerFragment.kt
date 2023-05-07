package io.github.seoj17.presentaion.ui.representative

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentRepresentativeSummonerBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.ui.dialog.NotFoundUserDialogFragment

@AndroidEntryPoint
class RepresentativeSummonerFragment :
    BaseFragment<FragmentRepresentativeSummonerBinding, RepresentativeSummonerViewModel>(
        FragmentRepresentativeSummonerBinding::inflate,
    ) {
    override val viewModel: RepresentativeSummonerViewModel by viewModels()

    override fun bindLayout() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun observeViewModel() {
        viewModel.searchResult.observe(viewLifecycleOwner) { summoner ->
            summoner?.let {
                findNavController().navigate(
                    RepresentativeSummonerFragmentDirections.actionRegisterSummonerToHome(it.name),
                )
            }
                ?: NotFoundUserDialogFragment().show(childFragmentManager, null)
        }
    }
}
