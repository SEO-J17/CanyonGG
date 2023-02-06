package io.github.seoj17.canyongg.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentRegisterSummonerBinding

@AndroidEntryPoint
class RegisterSummonerFragment : Fragment() {
    private lateinit var binding: FragmentRegisterSummonerBinding
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRegisterSummonerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navigator = Navigation.findNavController(view)
        val summonerName = "${binding.userEditName.text}"

        super.onViewCreated(view, savedInstanceState)
        binding.summonerSubmit.setOnClickListener {
            navigator.navigate(
                RegisterSummonerFragmentDirections.actionRegisterSummonerToHome(
                    summonerName
                )
            )
        }
    }
}