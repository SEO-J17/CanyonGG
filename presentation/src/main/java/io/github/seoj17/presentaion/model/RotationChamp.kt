package io.github.seoj17.presentaion.model

import androidx.recyclerview.widget.DiffUtil
import io.github.seoj17.domain.model.RotationChampionDomainModel

data class RotationChamp(
    val key: Int,
    val id: String,
    val name: String,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RotationChamp>() {
            override fun areItemsTheSame(
                oldItem: RotationChamp,
                newItem: RotationChamp,
            ): Boolean {
                return oldItem.key == newItem.key
            }

            override fun areContentsTheSame(
                oldItem: RotationChamp,
                newItem: RotationChamp,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: RotationChampionDomainModel): RotationChamp {
            return RotationChamp(
                key = domain.key,
                id = domain.id,
                name = domain.name,
            )
        }

        operator fun invoke(champList: List<RotationChampionDomainModel>): List<RotationChamp> {
            return champList.map {
                invoke(it)
            }
        }
    }
}
