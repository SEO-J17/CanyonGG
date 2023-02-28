package io.github.seoj17.canyongg.data.local.match

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MatchInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MatchInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<MatchInfoEntity>)

    @Query("DELETE FROM match_info WHERE summoner_puuid = :puuid")
    suspend fun delete(puuid: String)

    @Query("DELETE FROM match_info WHERE match_id = :matchId")
    suspend fun deleteMatchId(matchId: String)

    @Query("DELETE FROM match_info")
    suspend fun deleteAll()

    @Query("SELECT * FROM match_info WHERE summoner_puuid = :puuid")
    suspend fun getMyMatchInfo(puuid: String): List<MatchInfoEntity>

    @Query("SELECT * FROM match_info WHERE match_id = :matchId")
    suspend fun getParticipantsMatchInfo(matchId: String): List<MatchInfoEntity>
}