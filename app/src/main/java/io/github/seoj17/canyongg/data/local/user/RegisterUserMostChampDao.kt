package io.github.seoj17.canyongg.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterUserMostChampDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RegisterUserMostChampEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<RegisterUserMostChampEntity>)

    @Query("DELETE FROM register_user_most_champ WHERE user_puuid = :puuid")
    suspend fun delete(puuid: String)

    @Query("DELETE FROM register_user_most_champ")
    suspend fun deleteAll()

    @Query("SELECT * FROM register_user_most_champ")
    fun get(): Flow<List<RegisterUserMostChampEntity>>
}
