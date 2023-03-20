package io.github.seoj17.canyongg.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            email.addTextChangedListener {
                Patterns
                    .EMAIL_ADDRESS
                    .matcher(it.toString())
                    .run {
                        if (matches()) {
                            emailLayout.error = null
                            viewModel.fetchEmailValid(true)
                        } else {
                            emailLayout.error = getString(R.string.error_email_type)
                            viewModel.fetchEmailValid(false)
                        }
                    }
            }

            password.addTextChangedListener {
                if (it.isNullOrBlank()) {
                    passwordLayout.error = getString(R.string.login_password_message)
                    viewModel.fetchPasswordValid(false)
                } else {
                    passwordLayout.error = null
                    viewModel.fetchPasswordValid(true)
                }
            }

            viewModel.loginEvent.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(context, R.string.success_login, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToSetting(),
                    )
                } else {
                    Toast.makeText(context, R.string.fail_login, Toast.LENGTH_SHORT).show()
                }
            }

            register.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterUserFragment(),
                )
            }
        }
    }
}
