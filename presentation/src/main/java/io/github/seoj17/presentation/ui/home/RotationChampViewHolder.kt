package io.github.seoj17.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.presentation.model.RotationChamp
import io.github.seoj17.presentation.utils.setRotationChampion
import io.github.seoj17.presentation.databinding.ItemRotationBinding

class RotationChampViewHolder(
    private val binding: ItemRotationBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(rotationChamp: RotationChamp) {
        with(binding) {
            rotationChampImg.setRotationChampion(rotationChamp.name)
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
