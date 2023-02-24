package io.github.seoj17.canyongg.ui.detail.analysisTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.FragmentTeamAnalysisBinding

@AndroidEntryPoint
class TeamAnalysisFragment : Fragment() {
    private lateinit var binding: FragmentTeamAnalysisBinding
    private val args: TeamAnalysisFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTeamAnalysisBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            analysisTabPager.adapter =
                AnalysisViewPagerAdapter(this@TeamAnalysisFragment, args.matchId)
            TabLayoutMediator(analysisTabLayout, analysisTabPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.sub_pager_kill)
                    1 -> tab.text = getString(R.string.sub_pager_dealt)
                    2 -> tab.text = getString(R.string.sub_pager_damaged)
                    3 -> tab.text = getString(R.string.sub_pager_spent_gold)
                    4 -> tab.text = getString(R.string.sub_pager_minions)
                    5 -> tab.text = getString(R.string.sub_pager_vision_score)
                }
            }.attach()
        }
    }

    companion object {
        fun newInstance(matchId: String, puuid: String): TeamAnalysisFragment {
            return TeamAnalysisFragment().apply {
                arguments = Bundle().apply {
                    putString("matchId", matchId)
                    putString("puuid", puuid)
                }
            }
        }
    }
}