package io.github.seoj17.canyongg.data.local.perks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PerkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PerkEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<PerkEntity>)

    @Query("DELETE FROM perk_info WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM perk_info")
    suspend fun deleteAll()

    @Query("SELECT * FROM perk_info WHERE id = :id")
    suspend fun get(id: Int): PerkEntity

    @Query("SELECT * FROM perk_info WHERE name = :name")
    suspend fun get(name: String): PerkEntity
}