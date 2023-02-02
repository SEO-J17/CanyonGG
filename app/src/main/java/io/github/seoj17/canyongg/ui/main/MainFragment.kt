package io.github.seoj17.canyongg.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.FragmentMainBinding
import io.github.seoj17.canyongg.utils.observeEvent

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)

        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            tab1.setClickListener {
                navigator.navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
            }

            tab2.setClickListener {
                navigator.navigate(MainFragmentDirections.actionMainFragmentToSummonerSearchFragment())
            }

            viewModel.errorEvent.observeEvent(viewLifecycleOwner) {
                AlertDialog.Builder(this@MainFragment.context)
                    .setTitle(R.string.dialog_title)
                    .setMessage(R.string.dialog_message)
                    .setPositiveButton(R.string.dialog_positive) { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
            }
        }
    }

}