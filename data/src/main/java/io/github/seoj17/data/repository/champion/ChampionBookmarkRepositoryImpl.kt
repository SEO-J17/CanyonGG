package io.github.seoj17.data.repository.champion

import io.github.seoj17.data.local.bookmark.ChampionBookmarkDao
import io.github.seoj17.data.local.bookmark.ChampionBookmarkEntity
import io.github.seoj17.data.model.ChampionBookmarkDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChampionBookmarkRepositoryImpl @Inject constructor(
    private val championDao: ChampionBookmarkDao,
) : ChampionBookmarkRepository {
    override fun getChampion(): Flow<List<ChampionBookmarkDataModel>> {
        return championDao
            .getBookmarkChampion()
            .map {
                ChampionBookmarkDataModel(it)
            }
    }

    override suspend fun addBookmarkChampion(entity: ChampionBookmarkEntity) {
        championDao.insert(entity)
    }

    override suspend fun deleteBookmarkChampion(key: Int) {
        championDao.delete(key)
    }
}
