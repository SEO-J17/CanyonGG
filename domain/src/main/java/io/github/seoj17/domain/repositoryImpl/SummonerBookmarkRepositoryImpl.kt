package io.github.seoj17.domain.repositoryImpl

import io.github.seoj17.data.local.bookmark.SummonerBookmarkDao
import io.github.seoj17.data.local.bookmark.SummonerBookmarkEntity
import io.github.seoj17.data.model.SummonerBookmarkDataModel
import io.github.seoj17.data.repository.SummonerBookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SummonerBookmarkRepositoryImpl @Inject constructor(
    private val localService: SummonerBookmarkDao,
) : SummonerBookmarkRepository {
    override fun getBookmarkedSummoner(): Flow<List<SummonerBookmarkDataModel>> {
        return localService
            .getSummoners()
            .map {
                SummonerBookmarkDataModel(it)
            }
    }

    override suspend fun addBookmarkSummoner(entity: SummonerBookmarkEntity) {
        localService.insert(entity)
    }

    override suspend fun deleteBookmarkSummoner(puuid: String) {
        localService.delete(puuid)
    }

    override fun checkBookmarkedSummoner(puuid: String): Flow<Boolean> {
        return localService.isBookmarkedSummoner(puuid)
    }
}