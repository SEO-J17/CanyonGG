package io.github.seoj17.canyongg.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PerksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PerksEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<PerksEntity>)

    @Query("DELETE FROM perks_info WHERE perk_id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM perks_info")
    suspend fun deleteAll()

    @Query("SELECT * FROM perks_info WHERE perk_id = :id")
    suspend fun getPerk(id: Int): PerksEntity

    @Query("SELECT * FROM perks_info WHERE perk_name = :name")
    suspend fun getPerk(name: String): PerksEntity
}