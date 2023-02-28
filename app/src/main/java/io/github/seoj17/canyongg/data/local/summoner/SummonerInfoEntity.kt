package io.github.seoj17.canyongg.data.local.summoner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summoner_info")
data class SummonerInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "summoner_puuid")
    var puuid: String,
    @ColumnInfo(name = "summoner_profile")
    var profile: Int,
    @ColumnInfo(name = "summoner_level")
    var level: Int,
    @ColumnInfo(name = "summoner_name")
    var name: String,
    @ColumnInfo(name = "summoner_tier")
    var tier: String,
    @ColumnInfo(name = "summoner_whole_match")
    var wholeMatch: Int,
    @ColumnInfo(name = "summoner_win")
    var win: Int,
    @ColumnInfo(name = "summoner_lose")
    var lose: Int,
    @ColumnInfo(name = "summoner_win_rate")
    var winRate: Int,
    @ColumnInfo(name = "summoner_kda")
    var kda: Double,
    @ColumnInfo(name = "summoner_largest_kill")
    var largestKill: Int,
)