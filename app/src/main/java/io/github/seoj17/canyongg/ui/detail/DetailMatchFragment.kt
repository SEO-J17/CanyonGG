package io.github.seoj17.canyongg.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentDetailMatchBinding

@AndroidEntryPoint
class DetailMatchFragment : Fragment() {
    private lateinit var binding: FragmentDetailMatchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailMatchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tabPager.adapter = DetailMatchViewPagerAdapter(this@DetailMatchFragment)
            TabLayoutMediator(tabLayout, tabPager) { tab, position ->
                tab.setText(DetailMatchTabs.values()[position].title)
            }.attach()
        }
    }
}