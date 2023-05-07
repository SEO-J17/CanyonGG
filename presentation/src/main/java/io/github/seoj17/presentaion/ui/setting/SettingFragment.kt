package io.github.seoj17.presentaion.ui.setting

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.databinding.FragmentSettingBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment

@AndroidEntryPoint
class SettingFragment :
    BaseFragment<FragmentSettingBinding, SettingViewModel>(FragmentSettingBinding::inflate) {
    override val viewModel: SettingViewModel by viewModels()

    override fun bindLayout() {
        with(binding) {
            vm = viewModel

            login.setOnClickListener {
                findNavController().navigate(
                    SettingFragmentDirections.actionSettingToLoginFragment(),
                )
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

    override fun observeViewModel() = Unit
}
