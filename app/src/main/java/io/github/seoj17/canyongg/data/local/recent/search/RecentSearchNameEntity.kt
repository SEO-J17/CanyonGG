package io.github.seoj17.canyongg.data.local.recent.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_search")
data class RecentSearchNameEntity(
    @PrimaryKey
    @ColumnInfo(name = "puuid")
    val summonerPuuid: String,
    @ColumnInfo(name = "summoner_name")
    val summonerName: String,
)
