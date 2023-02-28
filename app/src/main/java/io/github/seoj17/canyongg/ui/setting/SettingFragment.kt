package io.github.seoj17.canyongg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.seoj17.canyongg.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}