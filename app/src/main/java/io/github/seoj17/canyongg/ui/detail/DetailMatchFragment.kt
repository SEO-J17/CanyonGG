package io.github.seoj17.canyongg.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.FragmentDetailMatchBinding

class DetailMatchFragment : Fragment() {
    private lateinit var binding: FragmentDetailMatchBinding
    private val args: DetailMatchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailMatchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tabPager.adapter = ViewPagerAdapter(this@DetailMatchFragment, args.matchId, args.puuid)
            TabLayoutMediator(tabLayout, tabPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.match_summary)
                    1 -> tab.text = getString(R.string.team_analysis)
                    2 -> tab.text = getString(R.string.my_rank)
                }
            }.attach()
        }
    }
}