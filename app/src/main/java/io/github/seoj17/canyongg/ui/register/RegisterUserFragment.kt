package io.github.seoj17.canyongg.ui.register

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
import io.github.seoj17.canyongg.databinding.FragmentRegisterUserBinding

@AndroidEntryPoint
class RegisterUserFragment : Fragment() {
    private lateinit var binding: FragmentRegisterUserBinding
    private val viewModel: RegisterUserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRegisterUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel

            email.addTextChangedListener {
                if (it.isNullOrBlank()) {
                    emailLayout.error = getString(R.string.error_email)
                    viewModel.fetchEmailValid(false)
                } else {
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
            }

            password.addTextChangedListener {
                if ((password.text?.length ?: 0) < 6) {
                    passwordLayout.error = getString(R.string.error_password)
                } else {
                    passwordLayout.error = null
                }
                chkPassword()
            }

            passwordChk.addTextChangedListener {
                chkPassword()
            }

            viewModel.registerEvent.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(context, R.string.success_register, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(
                        RegisterUserFragmentDirections.actionRegisterUserFragmentToLoginFragment(),
                    )
                } else {
                    Toast.makeText(context, R.string.fail_register, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun chkPassword() {
        with(binding) {
            if (
                password.text.toString() != passwordChk.text.toString() ||
                (passwordChk.text?.length ?: 0) < 6
            ) {
                passwordChkLayout.error = getString(R.string.error_chk_password)
                viewModel.fetchPasswordValid(false)
            } else {
                passwordChkLayout.error = null
                viewModel.fetchPasswordValid(true)
            }
        }
    }
}
