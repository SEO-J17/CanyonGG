package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkEntity
import io.github.seoj17.canyongg.data.model.SummonerBookmarkDataModel
import kotlinx.coroutines.flow.Flow

interface SummonerBookmarkRepository {
    fun getBookmarkedSummoner(): Flow<List<SummonerBookmarkDataModel>>
    suspend fun addBookmarkSummoner(entity: SummonerBookmarkEntity)
    suspend fun deleteBookmarkSummoner(puuid: String)
    fun checkBookmarkedSummoner(puuid: String): Flow<Boolean>
}