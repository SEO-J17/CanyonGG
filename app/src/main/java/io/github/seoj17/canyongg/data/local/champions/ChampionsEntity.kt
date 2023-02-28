package io.github.seoj17.canyongg.data.local.champions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "champions_info")
data class ChampionsEntity(
    @PrimaryKey
    @ColumnInfo(name = "champion_key")
    var key: Int,
    @ColumnInfo(name = "champion_name")
    var name: String,
)