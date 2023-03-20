package io.github.seoj17.canyongg.data.local.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summoner_bookmark")
data class SummonerBookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "puuid")
    val puuid: String,
    @ColumnInfo(name = "summoner_name")
    val summonerName: String,
    @ColumnInfo(name = "summoner_level")
    val level: Int,
    @ColumnInfo(name = "icon")
    val icon: Int,
)
