package io.github.seoj17.canyongg.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyMostChampDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MyMostChampEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<MyMostChampEntity>)

    @Query("DELETE FROM my_user_most_champ WHERE user_puuid = :puuid")
    suspend fun delete(puuid: String)

    @Query("DELETE FROM my_user_most_champ")
    suspend fun deleteAll()

    @Query("SELECT * FROM my_user_most_champ")
    fun getMostChamps(): Flow<List<MyMostChampEntity>>
}