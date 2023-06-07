package io.github.seoj17.presentaion.ui.representative

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.databinding.FragmentRepresentativeSummonerBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.ui.dialog.NotFoundUserDialogFragment

@AndroidEntryPoint
class RepresentativeSummonerFragment :
    BaseDataBindingFragment<FragmentRepresentativeSummonerBinding, RepresentativeSummonerViewModel>(
        FragmentRepresentativeSummonerBinding::inflate,
    ) {
    override val viewModel: RepresentativeSummonerViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() = Unit

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
