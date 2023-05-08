package io.github.seoj17.data.remote.response.champion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChampStats(
    @Json(name = "armor")
    val armor: Int,
    @Json(name = "armorperlevel")
    val armorPerLevel: Double,
    @Json(name = "attackdamage")
    val attackDamage: Int,
    @Json(name = "attackdamageperlevel")
    val attackDamagePerLevel: Double,
    @Json(name = "hp")
    val hp: Int,
    @Json(name = "hpperlevel")
    val hpPerLevel: Int,
    @Json(name = "movespeed")
    val moveSpeed: Int,
    @Json(name = "mp")
    val mp: Int,
    @Json(name = "mpperlevel")
    val mpPerLevel: Double,
    @Json(name = "spellblock")
    val spellBlock: Int,
    @Json(name = "spellblockperlevel")
    val spellBlockPerLevel: Double,
)
