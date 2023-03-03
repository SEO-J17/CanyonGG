package io.github.seoj17.canyongg.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterUserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RegisterUserInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<RegisterUserInfoEntity>)

    @Query("DELETE FROM register_user_info WHERE puuid = :puuid")
    suspend fun delete(puuid: String)

    @Query("DELETE FROM register_user_info")
    suspend fun deleteAll()

    @Query("SELECT * FROM register_user_info")
    fun get(): Flow<RegisterUserInfoEntity?>

}