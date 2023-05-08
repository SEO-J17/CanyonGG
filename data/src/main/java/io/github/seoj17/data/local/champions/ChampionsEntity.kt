package io.github.seoj17.data.local.champions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champions_info")
data class ChampionsEntity(
    @PrimaryKey
    @ColumnInfo(name = "champion_key")
    val key: Int,
    @ColumnInfo(name = "champion_id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "tag")
    val tag: String,
    @ColumnInfo(name = "blurb")
    val blurb: String,
    @ColumnInfo(name = "hp")
    val hp: Int,
    @ColumnInfo(name = "hp_per_level")
    val hpPerLevel: Int,
    @ColumnInfo(name = "move_speed")
    val moveSpeed: Int,
    @ColumnInfo(name = "attack_damage")
    val attackDamage: Int,
    @ColumnInfo(name = "attack_damage_perLevel")
    val attackDamagePerLevel: Double,
    @ColumnInfo(name = "armor")
    val armor: Int,
    @ColumnInfo(name = "armor_per_level")
    val armorPerLevel: Double,
    @ColumnInfo(name = "spell_block")
    val spellBlock: Int,
    @ColumnInfo(name = "spell_block_per_level")
    val spellBlockPerLevel: Double,
)
