package io.github.seoj17.presentaion.ui.champion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.presentaion.databinding.ItemChampionBinding
import io.github.seoj17.presentaion.model.Champion

class ChampionViewHolder(
    private val binding: ItemChampionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        champion: Champion,
        addBookmarkClick: (Champion) -> Unit,
        deleteBookmarkClick: (Champion) -> Unit,
    ) {
        with(binding) {
            data = champion
            championBookMark.setOnClickListener {
                if (it.isSelected) {
                    it.isSelected = false
                    deleteBookmarkClick(champion)
                } else {
                    it.isSelected = true
                    addBookmarkClick(champion)
                }
            }
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): ChampionViewHolder {
            return ChampionViewHolder(
                ItemChampionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
