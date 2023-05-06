package io.github.seoj17.presentaion.ui.representative

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentRepresentativeSummonerBinding
import io.github.seoj17.presentaion.ui.dialog.NotFoundUserDialogFragment
import io.github.seoj17.presentaion.ui.main.SharedViewModel

@AndroidEntryPoint
class RepresentativeSummonerFragment : Fragment() {
    private lateinit var binding: FragmentRepresentativeSummonerBinding
    private val viewModel: RepresentativeSummonerViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRepresentativeSummonerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel

            viewModel.summonerInfo.observe(viewLifecycleOwner) { summoner ->
                summoner?.let {
                    sharedViewModel.fetchRepresentativeUser(it)
                    findNavController().navigate(
                        RepresentativeSummonerFragmentDirections.actionRegisterSummonerToHome(
                            "",
                        ),
                    )
                }
                    ?: NotFoundUserDialogFragment().show(childFragmentManager, null)
            }
        }
    }
}
