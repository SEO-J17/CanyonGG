package io.github.seoj17.presentaion.ui.register

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentRegisterUserBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.utils.showToast
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterUserFragment : BaseFragment<FragmentRegisterUserBinding, RegisterUserViewModel>(
    FragmentRegisterUserBinding::inflate,
) {
    override val viewModel: RegisterUserViewModel by viewModels()

    override fun bindLayout() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.registerState.collect { state ->
                    when (state) {
                        is RegisterState.SUCCESS -> {
                            requireActivity().showToast(R.string.success_register)
                            findNavController().navigate(
                                RegisterUserFragmentDirections.actionRegisterUserFragmentToLoginFragment(),
                            )
                        }
                        is RegisterState.FAIL -> {
                            requireActivity().showToast(R.string.fail_register)
                        }
                    }
                }
            }
        }
    }
}
