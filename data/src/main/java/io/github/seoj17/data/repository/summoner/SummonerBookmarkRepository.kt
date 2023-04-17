package io.github.seoj17.data.repository.summoner

import io.github.seoj17.data.local.bookmark.SummonerBookmarkEntity
import io.github.seoj17.data.model.SummonerBookmarkDataModel
import kotlinx.coroutines.flow.Flow

interface SummonerBookmarkRepository {
    fun getBookmarkedSummoner(): Flow<List<SummonerBookmarkDataModel>>
    suspend fun addBookmarkSummoner(entity: SummonerBookmarkEntity)
    suspend fun deleteBookmarkSummoner(puuid: String)
    fun checkBookmarkedSummoner(puuid: String): Flow<Boolean>
}
