package io.github.seoj17.canyongg.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_user_info")
data class MyUserInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "my_user_puuid")
    var puuid: String,
    @ColumnInfo(name = "my_user_profile")
    var profile: Int,
    @ColumnInfo(name = "my_user_level")
    var level: Int,
    @ColumnInfo(name = "my_user_name")
    var name: String,
    @ColumnInfo(name = "my_user_tier")
    var tier: String,
    @ColumnInfo(name = "my_user_whole_match")
    var wholeMatch: Int,
    @ColumnInfo(name = "my_user_win")
    var win: Int,
    @ColumnInfo(name = "my_user_lose")
    var lose: Int,
    @ColumnInfo(name = "my_user_win_rate")
    var winRate: Int,
    @ColumnInfo(name = "my_user_kda")
    var kda: Double,
)