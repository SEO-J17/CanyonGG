package io.github.seoj17.canyongg.ui.representative

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentRepresentativeSummonerBinding
import io.github.seoj17.canyongg.ui.dialog.NotFoundUserDialogFragment
import io.github.seoj17.canyongg.utils.observeEvent

@AndroidEntryPoint
class RepresentativeSummonerFragment : Fragment() {
    private lateinit var binding: FragmentRepresentativeSummonerBinding
    private val viewModel: RepresentativeSummonerViewModel by viewModels()

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

            viewModel.searchResult.observe(viewLifecycleOwner) { summoner ->
                summoner?.let {
                    findNavController().navigate(
                        RepresentativeSummonerFragmentDirections.actionRegisterSummonerToHome(it.name),
                    )
                }
            }

            viewModel.errorEvent.observeEvent(viewLifecycleOwner) {
                NotFoundUserDialogFragment().show(childFragmentManager, null)
            }
        }
    }
}
