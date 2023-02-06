package io.github.seoj17.canyongg.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.seoj17.canyongg.databinding.FragmentMatchSummaryBinding

class MatchSummaryFragment : Fragment() {
    private lateinit var binding: FragmentMatchSummaryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMatchSummaryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}