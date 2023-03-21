package io.github.seoj17.canyongg.ui.detail.rankTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.databinding.FragmentMyRankBinding
import io.github.seoj17.canyongg.ui.detail.DetailMatchViewModel

@AndroidEntryPoint
class MyRankFragment : Fragment() {
    private lateinit var binding: FragmentMyRankBinding
    private val viewModel: MyRankViewModel by viewModels()
    private val parentViewModel: DetailMatchViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMyRankBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel

            viewModel.setMatchId(parentViewModel.matchId)
            viewModel.setSummonerPuuid(parentViewModel.puuId)
        }
    }

    companion object {
        fun newInstance() = MyRankFragment()
    }
}
