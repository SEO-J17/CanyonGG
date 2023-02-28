package io.github.seoj17.canyongg.data.local.bookmark

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SummonerBookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SummonerBookmarkEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<SummonerBookmarkEntity>)

    @Query("DELETE FROM summoner_bookmark WHERE summoner_name = :puuid")
    suspend fun delete(puuid: String)

    @Query("DELETE FROM summoner_bookmark")
    suspend fun deleteAll()

    @Query("SELECT * FROM summoner_bookmark WHERE summoner_name = :name")
    suspend fun getName(name: String): SummonerBookmarkEntity?

    @Query("SELECT * FROM summoner_bookmark")
    fun getSummoners(): Flow<List<SummonerBookmarkEntity>>

    @Query("SELECT EXISTS(SELECT * FROM summoner_bookmark WHERE summoner_puuid = :puuid)")
    suspend fun isBookmarkedSummoner(puuid: String): Boolean
}