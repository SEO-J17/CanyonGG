package io.github.seoj17.presentaion.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentSettingBinding

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel

            login.setOnClickListener {
                findNavController().navigate(
                    SettingFragmentDirections.actionSettingToLoginFragment(),
                )
            }

            viewModel.themeSetting.observe(viewLifecycleOwner) {
                when (it) {
                    ThemeState.LIGHT.mode -> lightTheme.isChecked = true
                    ThemeState.DARK.mode -> darkTheme.isChecked = true
                    else -> systemTheme.isChecked = true
                }
            }

            themeGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    lightTheme.id -> {
                        viewModel.fetchThemeSetting(index = 1)
                    }
                    darkTheme.id -> {
                        viewModel.fetchThemeSetting(index = 2)
                    }
                    else -> {
                        viewModel.fetchThemeSetting(index = 0)
                    }
                }
            }
        }
    }
}