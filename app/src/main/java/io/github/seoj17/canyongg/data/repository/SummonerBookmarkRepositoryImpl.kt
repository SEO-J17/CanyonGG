package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkDao
import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SummonerBookmarkRepositoryImpl @Inject constructor(
    private val localService: SummonerBookmarkDao,
) : SummonerBookmarkRepository {
    override fun getBookmarkedSummoner(): Flow<List<SummonerBookmarkEntity>> {
        return localService.getSummoners()
    }

    override suspend fun addBookmarkSummoner(entity: SummonerBookmarkEntity) {
        localService.insert(entity)
    }

    override suspend fun deleteBookmarkSummoner(puuid: String) {
        localService.delete(puuid)
    }

    override suspend fun checkBookmarkedSummoner(puuid: String): Boolean {
        return localService.isBookmarkedSummoner(puuid)
    }
}