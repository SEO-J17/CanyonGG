package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkEntity
import kotlinx.coroutines.flow.Flow

interface SummonerBookmarkRepository {
    fun getBookmarkedSummoner(): Flow<List<SummonerBookmarkEntity>>
    suspend fun addBookmarkSummoner(entity: SummonerBookmarkEntity)
    suspend fun deleteBookmarkSummoner(puuid: String)
    suspend fun checkBookmarkedSummoner(puuid: String): Boolean
}