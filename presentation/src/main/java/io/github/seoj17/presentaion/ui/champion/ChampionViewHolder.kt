package io.github.seoj17.presentaion.ui.champion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.presentaion.databinding.ItemChampionBinding
import io.github.seoj17.presentaion.model.Champion

class ChampionViewHolder(
    private val binding: ItemChampionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(champion: Champion) {
        with(binding) {
            data = champion
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
