package io.github.seoj17.presentation.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.presentation.model.SummonerBookmark

class BookmarkListAdapter(
    private val deleteClickListener: (String) -> Unit,
    private val itemClickListener: (String, String) -> Unit,
) : ListAdapter<SummonerBookmark, BookmarkListViewHolder>(SummonerBookmark.diffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = BookmarkListViewHolder(parent)

    override fun onBindViewHolder(holder: BookmarkListViewHolder, position: Int) {
        val markedSummoner = getItem(position)
        with(holder) {
            bind(markedSummoner, deleteClickListener)
            itemView.setOnClickListener {
                itemClickListener(markedSummoner.summonerName, markedSummoner.summonerPuuid)
            }
        }
    }
}
