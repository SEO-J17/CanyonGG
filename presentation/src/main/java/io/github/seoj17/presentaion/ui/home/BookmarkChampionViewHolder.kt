package io.github.seoj17.presentaion.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.presentaion.databinding.ItemBookmarkChampionBinding
import io.github.seoj17.presentaion.model.ChampionBookmark

class BookmarkChampionViewHolder(
    private val binding: ItemBookmarkChampionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(champion: ChampionBookmark, deleteClickListener: (Int) -> Unit) {
        with(binding) {
            data = champion
            deleteBookmark.setOnClickListener {
                deleteClickListener(champion.key)
            }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): BookmarkChampionViewHolder {
            return BookmarkChampionViewHolder(
                ItemBookmarkChampionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
