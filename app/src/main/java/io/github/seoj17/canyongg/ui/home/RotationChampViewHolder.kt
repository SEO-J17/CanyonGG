package io.github.seoj17.canyongg.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.databinding.ItemRotationBinding
import io.github.seoj17.canyongg.utils.setRotationChampion

class RotationChampViewHolder(
    private val binding: ItemRotationBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(champName: String) {
        with(binding) {
            rotationChampImg.setRotationChampion(champName)
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): RotationChampViewHolder {
            return RotationChampViewHolder(
                ItemRotationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
