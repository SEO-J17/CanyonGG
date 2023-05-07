package io.github.seoj17.presentaion.ui.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentLoginBinding
import io.github.seoj17.presentaion.ui.base.BaseFragment
import io.github.seoj17.presentaion.utils.showToast
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate,
) {
    override val viewModel: LoginViewModel by viewModels()

    override fun bindLayout() {
        with(binding) {
            vm = viewModel

            register.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterUserFragment(),
                )
            }
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loginState.collect { state ->
                    when (state) {
                        is LoginState.SUCCESS -> {
                            requireActivity().showToast(R.string.success_login)
                            findNavController().navigate(
                                LoginFragmentDirections.actionLoginFragmentToSetting(),
                            )
                        }
                        is LoginState.FAIL -> {
                            requireActivity().showToast(R.string.fail_login)
                        }
                    }
                }
            }
        }
    }
}
