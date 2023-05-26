package io.github.seoj17.presentaion.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.presentaion.model.ChampionBookmark

class BookmarkChampionListAdapter(
    private val deleteClickListener: (Int) -> Unit,
    private val itemClickListener: (Int) -> Unit,
) : ListAdapter<ChampionBookmark, BookmarkChampionViewHolder>(ChampionBookmark.diffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = BookmarkChampionViewHolder(parent)

    override fun onBindViewHolder(holder: BookmarkChampionViewHolder, position: Int) {
        val champion = getItem(position)
        with(holder) {
            bind(champion, deleteClickListener)
            itemView.setOnClickListener {
                itemClickListener(champion.key)
            }
        }
    }
}
