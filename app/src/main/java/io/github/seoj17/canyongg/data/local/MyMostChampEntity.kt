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
    @ColumnInfo(name = "user_puuid")
    var userPuuid: String,
    @ColumnInfo(name = "first_champ_name")
    var firstMostChamp: String,
    @ColumnInfo(name = "second_champ_name")
    var secondMostChamp: String,
    @ColumnInfo(name = "third_champ_name")
    var thirdMostChamp: String,
    @ColumnInfo(name = "first_kda")
    var firstChampKda: Double,
    @ColumnInfo(name = "second_kda")
    var secondChampKda: Double,
    @ColumnInfo(name = "third_kda")
    var thirdChampKda: Double,
    @ColumnInfo(name = "first_win_rate")
    var firstChampWinRate: Int,
    @ColumnInfo(name = "second_kda_win_rate")
    var secondChampWinRate: Int,
    @ColumnInfo(name = "third_kda_win_rate")
    var thirdChampWinRate: Int,
)