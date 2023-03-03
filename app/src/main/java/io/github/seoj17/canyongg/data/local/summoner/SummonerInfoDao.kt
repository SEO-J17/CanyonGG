package io.github.seoj17.canyongg.data.local.summoner

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SummonerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SummonerInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<SummonerInfoEntity>)

    @Query("DELETE FROM summoner_info WHERE puuid = :puuid")
    suspend fun delete(puuid: String)

    @Query("DELETE FROM summoner_info")
    suspend fun deleteAll()

    @Query("SELECT * FROM summoner_info WHERE puuid = :puuid")
    fun get(puuid: String): Flow<SummonerInfoEntity?>

    @Query("SELECT EXISTS(SELECT * FROM summoner_info WHERE puuid = :puuid)")
    suspend fun isMyUser(puuid: String): Boolean
}