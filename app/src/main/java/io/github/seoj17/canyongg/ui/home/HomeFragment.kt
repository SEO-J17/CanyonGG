package io.github.seoj17.canyongg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentHomeBinding
import io.github.seoj17.canyongg.ui.dialog.NotFoundUserDialogFragment
import io.github.seoj17.canyongg.ui.main.HomeFragmentDirections
import io.github.seoj17.canyongg.utils.observeEvent

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)

        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            tab1.setClickListener {
                navigator.navigate(HomeFragmentDirections.actionHomeToRegisterSummoner())
                viewModel.fetchUserInfo()
            }

            tab2.setClickListener {
                navigator.navigate(HomeFragmentDirections.actionHomeToSearchSummoner())
            }

            viewModel.errorEvent.observeEvent(viewLifecycleOwner) {
                NotFoundUserDialogFragment().show(childFragmentManager, null)
            }
        }
    }
}