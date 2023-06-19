package io.github.seoj17.presentaion.model

import androidx.recyclerview.widget.DiffUtil

@JvmInline
value class RotationChamp(
    val name: String,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RotationChamp>() {
            override fun areItemsTheSame(
                oldItem: RotationChamp,
                newItem: RotationChamp,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RotationChamp,
                newItem: RotationChamp,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(nameList: List<String>): List<RotationChamp> {
            return nameList.map {
                RotationChamp(it)
            }
        }
    }
}
