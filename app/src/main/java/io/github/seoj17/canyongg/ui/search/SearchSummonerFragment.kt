package io.github.seoj17.canyongg.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentSearchSummonerBinding

@AndroidEntryPoint
class SearchSummonerFragment : Fragment() {
    private lateinit var binding: FragmentSearchSummonerBinding
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSearchSummonerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        binding.temp.setOnClickListener {
            navigator.navigate(
                SearchSummonerFragmentDirections.actionSearchSummonerToSearchResult()
            )
        }
    }
}