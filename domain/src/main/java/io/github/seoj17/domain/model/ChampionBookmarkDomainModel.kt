package io.github.seoj17.domain.model

import io.github.seoj17.data.local.bookmark.ChampionBookmarkEntity
import io.github.seoj17.data.model.ChampionBookmarkDataModel

data class ChampionBookmarkDomainModel(
    val key: Int,
    val id: String,
    val name: String,
) {
    companion object {
        operator fun invoke(data: ChampionBookmarkDataModel): ChampionBookmarkDomainModel {
            return ChampionBookmarkDomainModel(
                key = data.key,
                id = data.id,
                name = data.name,
            )
        }

        operator fun invoke(list: List<ChampionBookmarkDataModel>): List<ChampionBookmarkDomainModel> {
            return list.map {
                invoke(it)
            }
        }

        fun toEntity(domain: ChampionBookmarkDomainModel): ChampionBookmarkEntity {
            return ChampionBookmarkEntity(
                key = domain.key,
                id = domain.id,
                name = domain.name,
            )
        }
    }
}
