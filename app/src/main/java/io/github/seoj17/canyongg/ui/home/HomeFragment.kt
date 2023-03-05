package io.github.seoj17.canyongg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            registerUserTab.setClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeToRegisterSummoner())
            }

            searchSummonerTab.setClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeToSearchGraph())
            }

            summonerTab.setClickListener {
                viewModel.removeMyInfo()
                Toast.makeText(
                    this@HomeFragment.context,
                    getString(R.string.info_delete_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }

            summonerTab.setRefreshClickListener {
                viewModel.refreshMyInfo()
                Toast.makeText(
                    this@HomeFragment.context,
                    getString(R.string.infro_refresh_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }

            detailMyInfo.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToSearchGraph(
                        summonerName = viewModel.userInfo.value?.name,
                        summonerPuuid = viewModel.userInfo.value?.puuid,
                    )
                )
            }

            bookMarkList.adapter = BookmarkListAdapter(
                { deleteName ->
                    viewModel.removeBookmark(deleteName)
                }
            ) { name, puuid ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToSearchGraph(
                        summonerName = name,
                        summonerPuuid = puuid,
                    )
                )
            }
            champRotationListView.adapter = RotationChampListAdapter()
        }
    }
}