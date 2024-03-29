package io.github.seoj17.data.local.champions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ChampionsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<ChampionsEntity>)

    @Query("DELETE FROM champions_info WHERE name = :name")
    suspend fun delete(name: String)

    @Query("DELETE FROM champions_info WHERE champion_key = :key")
    suspend fun delete(key: Int)

    @Query("DELETE FROM champions_info")
    suspend fun deleteAll()

    @Query("SELECT * FROM champions_info WHERE name = :name")
    suspend fun get(name: String): ChampionsEntity?

    @Query("SELECT * FROM champions_info WHERE champion_key = :key")
    suspend fun get(key: Int): ChampionsEntity?

    @Query("SELECT * FROM champions_info ORDER BY name ASC")
    fun getAll(): Flow<List<ChampionsEntity>>
}
