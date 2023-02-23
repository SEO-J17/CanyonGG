package io.github.seoj17.canyongg.ui.detail.analysisTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
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
                    0 -> tab.text = "챔피언 킬"
                    1 -> tab.text = "가한 피해량"
                    2 -> tab.text = "받은 피해량"
                    3 -> tab.text = "골드"
                    4 -> tab.text = "미니언"
                    5 -> tab.text = "와드"
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