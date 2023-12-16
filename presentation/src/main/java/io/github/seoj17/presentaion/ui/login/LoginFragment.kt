package io.github.seoj17.presentaion.ui.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentaion.BR
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.FragmentLoginBinding
import io.github.seoj17.presentaion.ui.base.BaseDataBindingFragment
import io.github.seoj17.presentaion.utils.showToast
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseDataBindingFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate,
) {
    override val viewModel: LoginViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun bindLayout() {
        with(binding) {
            register.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterUserFragment(),
                )
            }

            toolbar.setOnClickListener {
                requireActivity().onBackPressed()
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
                            moveToSettingFragment()
                        }
                        is LoginState.FAIL -> {
                            requireActivity().showToast(R.string.fail_login)
                        }
                    }
                }
            }
        }
    }

    private fun moveToSettingFragment() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToSetting(),
        )
    }
}
