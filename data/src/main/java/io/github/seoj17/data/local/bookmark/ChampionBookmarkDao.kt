package io.github.seoj17.data.local.bookmark

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionBookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ChampionBookmarkEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<ChampionBookmarkEntity>)

    @Query("DELETE FROM champion_bookmark WHERE champ_key = :key")
    suspend fun delete(key: Int)

    @Query("DELETE FROM champion_bookmark")
    suspend fun deleteAll()

    @Query("SELECT * FROM champion_bookmark")
    fun getBookmarkChampion(): Flow<List<ChampionBookmarkEntity>>
}
