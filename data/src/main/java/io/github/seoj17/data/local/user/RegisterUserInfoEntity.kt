package io.github.seoj17.data.local.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_user_info")
data class RegisterUserInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "puuid")
    var puuid: String,
    @ColumnInfo(name = "profile")
    var profile: Int,
    @ColumnInfo(name = "level")
    var level: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "tier")
    var tier: String,
    @ColumnInfo(name = "match")
    var wholeMatch: Int,
    @ColumnInfo(name = "win")
    var win: Int,
    @ColumnInfo(name = "lose")
    var lose: Int,
    @ColumnInfo(name = "rate")
    var winRate: Int,
    @ColumnInfo(name = "kda")
    var kda: Double,
)
