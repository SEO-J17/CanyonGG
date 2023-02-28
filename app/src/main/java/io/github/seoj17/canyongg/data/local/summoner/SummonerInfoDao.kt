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

    @Query("DELETE FROM summoner_info WHERE summoner_puuid = :puuid")
    suspend fun delete(puuid: String)

    @Query("DELETE FROM summoner_info")
    suspend fun deleteAll()

    @Query("SELECT * FROM summoner_info WHERE summoner_puuid = :puuid")
    fun getSummonerInfo(puuid: String): Flow<SummonerInfoEntity?>

    @Query("SELECT EXISTS(SELECT * FROM my_user_info WHERE my_user_puuid = :puuid)")
    suspend fun isMyUser(puuid: String): Boolean
}