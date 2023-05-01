package io.github.seoj17.presentation.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.presentation.utils.showToast
import io.github.seoj17.presentation.R
import io.github.seoj17.presentation.databinding.FragmentRegisterUserBinding
import kotlinx.coroutines.launch

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
        }

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
