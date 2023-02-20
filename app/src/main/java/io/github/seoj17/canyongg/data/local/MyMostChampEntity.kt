package io.github.seoj17.canyongg.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "my_user_most_champ",
    foreignKeys = [
        ForeignKey(
            entity = MyUserInfoEntity::class,
            parentColumns = arrayOf("my_user_puuid"),
            childColumns = arrayOf("user_puuid"),
            onDelete = ForeignKey.CASCADE,
        )
    ]
)

data class MyMostChampEntity(
    @PrimaryKey
    @ColumnInfo(name = "champ_name")
    var champName: String,
    @ColumnInfo(name = "user_puuid")
    var userPuuid: String,
    @ColumnInfo(name = "champ_kda")
    var champKda: Double,
    @ColumnInfo(name = "champ_win_rate")
    var champWinRate: Int,
)