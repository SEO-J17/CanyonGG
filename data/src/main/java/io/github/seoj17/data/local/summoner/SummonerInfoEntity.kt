package io.github.seoj17.data.local.summoner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summoner_info")
data class SummonerInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "puuid")
    val puuid: String,
    @ColumnInfo(name = "profile")
    val profile: Int,
    @ColumnInfo(name = "level")
    val level: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "tier")
    val tier: String,
    @ColumnInfo(name = "whole_match")
    val wholeMatch: Int,
    @ColumnInfo(name = "win")
    val win: Int,
    @ColumnInfo(name = "lose")
    val lose: Int,
    @ColumnInfo(name = "win_rate")
    val winRate: Int,
    @ColumnInfo(name = "kda")
    val kda: Double,
    @ColumnInfo(name = "largest_kill")
    val largestKill: Int,
)
