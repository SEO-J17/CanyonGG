package io.github.seoj17.canyongg.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_search")
data class RecentSearchNameEntity(
    @PrimaryKey
    @ColumnInfo(name = "summoner_puuid")
    var summonerPuuid: String,
    @ColumnInfo(name = "summoner_name")
    var summonerName: String,
)