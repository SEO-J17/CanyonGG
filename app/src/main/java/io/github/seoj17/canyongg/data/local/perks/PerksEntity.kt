package io.github.seoj17.canyongg.data.local.perks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perks_info")
data class PerksEntity(
    @PrimaryKey
    @ColumnInfo(name = "perk_id")
    var id: Int,
    @ColumnInfo(name = "perk_name")
    var name: String,
    @ColumnInfo(name = "perk_img_url")
    var imgUrl: String,
)