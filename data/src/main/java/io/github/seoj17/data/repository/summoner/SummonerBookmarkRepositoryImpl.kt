package io.github.seoj17.data.repository.summoner

import io.github.seoj17.data.local.bookmark.SummonerBookmarkDao
import io.github.seoj17.data.local.bookmark.SummonerBookmarkEntity
import io.github.seoj17.data.model.SummonerBookmarkDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SummonerBookmarkRepositoryImpl @Inject constructor(
    private val summonerBookmarkDao: SummonerBookmarkDao,
) : SummonerBookmarkRepository {
    override fun getBookmarkedSummoner(): Flow<List<SummonerBookmarkDataModel>> {
        return summonerBookmarkDao
            .getSummoners()
            .map {
                SummonerBookmarkDataModel(it)
            }
    }

    override suspend fun addBookmarkSummoner(entity: SummonerBookmarkEntity) {
        summonerBookmarkDao.insert(entity)
    }

    override suspend fun deleteBookmarkSummoner(puuid: String) {
        summonerBookmarkDao.delete(puuid)
    }

    override fun checkBookmarkedSummoner(puuid: String): Flow<Boolean> {
        return summonerBookmarkDao.isBookmarkedSummoner(puuid)
    }
}
