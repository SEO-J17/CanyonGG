package io.github.seoj17.data.repository.champion

import io.github.seoj17.data.local.bookmark.ChampionBookmarkEntity
import io.github.seoj17.data.model.ChampionBookmarkDataModel
import kotlinx.coroutines.flow.Flow

interface ChampionBookmarkRepository {
    fun getChampion(): Flow<List<ChampionBookmarkDataModel>>
    suspend fun addBookmarkChampion(entity: ChampionBookmarkEntity)
    suspend fun deleteBookmarkChampion(key: Int)
}
