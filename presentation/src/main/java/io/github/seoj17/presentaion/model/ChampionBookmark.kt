package io.github.seoj17.presentaion.model

import androidx.recyclerview.widget.DiffUtil
import io.github.seoj17.domain.model.ChampionBookmarkDomainModel

data class ChampionBookmark(
    val key: Int,
    val id: String,
    val name: String,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChampionBookmark>() {
            override fun areItemsTheSame(
                oldItem: ChampionBookmark,
                newItem: ChampionBookmark,
            ): Boolean {
                return oldItem.key == newItem.key
            }

            override fun areContentsTheSame(
                oldItem: ChampionBookmark,
                newItem: ChampionBookmark,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: ChampionBookmarkDomainModel): ChampionBookmark {
            return ChampionBookmark(
                key = domain.key,
                id = domain.id,
                name = domain.name,
            )
        }

        fun toDomain(champion: Champion): ChampionBookmarkDomainModel {
            return ChampionBookmarkDomainModel(
                key = champion.key,
                id = champion.id,
                name = champion.name,
            )
        }

        operator fun invoke(list: List<ChampionBookmarkDomainModel>): List<ChampionBookmark> {
            return list.map {
                invoke(it)
            }
        }
    }
}
