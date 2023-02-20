package io.github.seoj17.canyongg.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyUserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MyUserInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<MyUserInfoEntity>)

    @Query("DELETE FROM my_user_info WHERE my_user_puuid = :puuid")
    suspend fun delete(puuid: String)

    @Query("DELETE FROM my_user_info")
    suspend fun deleteAll()

    @Query("SELECT * FROM my_user_info")
    fun getMyUserInfo(): Flow<MyUserInfoEntity?>

}