package io.github.seoj17.canyongg.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.databinding.ItemBookmarkSummonerBinding
import io.github.seoj17.canyongg.ui.model.SummonerBookmark

class BookmarkListViewHolder(
    private val binding: ItemBookmarkSummonerBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(markedSummoner: SummonerBookmark, deleteClickListener: (String) -> Unit) {
        with(binding) {
            data = markedSummoner
            deleteBookmark.setOnClickListener {
                deleteClickListener(markedSummoner.summonerName)
            }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): BookmarkListViewHolder {
            return BookmarkListViewHolder(
                ItemBookmarkSummonerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
