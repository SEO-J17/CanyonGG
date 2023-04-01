package io.github.seoj17.presentaion.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.presentaion.model.RecentSummoners

class SearchSummonerListAdapter(
    private val deleteClickListener: (String) -> Unit,
    private val itemClickListener: (String, String) -> Unit,
) : ListAdapter<RecentSummoners, SearchSummonerListViewHolder>(RecentSummoners.diffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = SearchSummonerListViewHolder(parent)

    override fun onBindViewHolder(holder: SearchSummonerListViewHolder, position: Int) {
        val summoner = getItem(position) ?: return
        with(holder) {
            bind(summoner, deleteClickListener)

            itemView.setOnClickListener {
                itemClickListener(summoner.name, summoner.puuid)
            }
        }
    }
}
