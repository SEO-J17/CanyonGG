package io.github.seoj17.presentaion.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.presentaion.databinding.ItemRotationBinding
import io.github.seoj17.presentaion.utils.setRotationChampion

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
