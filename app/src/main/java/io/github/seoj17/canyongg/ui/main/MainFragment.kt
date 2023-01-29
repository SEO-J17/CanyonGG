package io.github.seoj17.canyongg.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentMainBinding
import io.github.seoj17.canyongg.utils.OnButtonClickListener
import io.github.seoj17.canyongg.utils.observeEvent
import kotlinx.coroutines.launch

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

            tab1.onApplyClickListener(object : OnButtonClickListener {
                override fun onButtonClick(tabState: Int) {
                    if (tabState == 0) {
                        navigator.navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
                        lifecycleScope.launch {
                            viewModel.fetchUserInfo()
                        }
                    }
                }
            })

            viewModel.errorEvent.observeEvent(viewLifecycleOwner) {
                AlertDialog.Builder(this@MainFragment.context)
                    .setTitle("일치하는 이름의 소환사가 없습니다!")
                    .setMessage("소환사 이름을 다시 확인해주세요.")
                    .setPositiveButton("확인") { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
            }
        }
    }
}