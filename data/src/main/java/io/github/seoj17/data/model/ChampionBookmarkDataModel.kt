package io.github.seoj17.data.model

import io.github.seoj17.data.local.bookmark.ChampionBookmarkEntity

data class ChampionBookmarkDataModel(
    val key: Int,
    val id: String,
    val name: String,
) {
    companion object {
        operator fun invoke(entity: ChampionBookmarkEntity): ChampionBookmarkDataModel {
            return ChampionBookmarkDataModel(
                key = entity.key,
                id = entity.id,
                name = entity.name,
            )
        }

        operator fun invoke(list: List<ChampionBookmarkEntity>): List<ChampionBookmarkDataModel> {
            return list.map {
                invoke(it)
            }
        }
    }
}
