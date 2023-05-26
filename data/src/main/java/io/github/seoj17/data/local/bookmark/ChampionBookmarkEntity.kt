package io.github.seoj17.data.local.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champion_bookmark")
data class ChampionBookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "champ_key")
    val key: Int,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
)
