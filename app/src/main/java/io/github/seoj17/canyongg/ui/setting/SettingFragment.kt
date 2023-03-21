package io.github.seoj17.canyongg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentSettingBinding

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
                if (it == AppCompatDelegate.MODE_NIGHT_YES) {
                    darkTheme.isChecked = true
                } else {
                    lightTheme.isChecked = true
                }
            }

            themeGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    darkTheme.id -> {
                        viewModel.fetchThemeSetting(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else -> {
                        viewModel.fetchThemeSetting(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }
    }
}
