package io.github.seoj17.canyongg.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "summoner_bookmark")
data class SummonerBookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "summoner_puuid")
    var summonerPuuid: String,
    @ColumnInfo(name = "summoner_name")
    var summonerName: String,
    @ColumnInfo(name = "summoner_level")
    var summonerLevel: Int,
    @ColumnInfo(name = "summoner_icon")
    var summonerIcon: Int,
)