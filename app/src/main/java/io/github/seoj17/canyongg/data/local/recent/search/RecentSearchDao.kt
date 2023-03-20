package io.github.seoj17.canyongg.data.local.recent.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RecentSearchNameEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<RecentSearchNameEntity>)

    @Query("DELETE FROM recent_search WHERE summoner_name = :name")
    suspend fun delete(name: String)

    @Query("DELETE FROM recent_search")
    suspend fun deleteAll()

    @Query("SELECT * FROM recent_search WHERE summoner_name = :name")
    suspend fun getName(name: String): RecentSearchNameEntity?

    @Query("SELECT * FROM recent_search")
    fun get(): Flow<List<RecentSearchNameEntity>>
}
