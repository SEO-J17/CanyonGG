package io.github.seoj17.canyongg.data.local.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "register_user_most_champ",
    foreignKeys = [
        ForeignKey(
            entity = RegisterUserInfoEntity::class,
            parentColumns = arrayOf("puuid"),
            childColumns = arrayOf("user_puuid"),
            onDelete = ForeignKey.CASCADE,
        )
    ]
)

data class RegisterUserMostChampEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val champName: String,
    @ColumnInfo(name = "user_puuid")
    val userPuuid: String,
    @ColumnInfo(name = "kda")
    val champKda: Double,
    @ColumnInfo(name = "win_rate")
    val champWinRate: Int,
)