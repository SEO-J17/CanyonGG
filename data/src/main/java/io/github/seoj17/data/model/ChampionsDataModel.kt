package io.github.seoj17.data.model

import io.github.seoj17.data.local.champions.ChampionsEntity
import io.github.seoj17.data.remote.response.champion.ChampData

data class ChampionsDataModel(
    val key: Int,
    val id: String,
    val name: String,
    val title: String,
    val tags: String,
    val blurb: String,
    val hp: Int,
    val hpPerLevel: Int,
    val moveSpeed: Int,
    val attackDamage: Int,
    val attackDamagePerLevel: Double,
    val armor: Int,
    val armorPerLevel: Double,
    val spellBlock: Int,
    val spellBlockPerLevel: Double,
) {
    companion object {
        operator fun invoke(entity: ChampionsEntity): ChampionsDataModel {
            return ChampionsDataModel(
                key = entity.key,
                id = entity.id,
                name = entity.name,
                title = entity.title,
                tags = entity.tag,
                blurb = entity.blurb,
                hp = entity.hp,
                hpPerLevel = entity.hpPerLevel,
                moveSpeed = entity.moveSpeed,
                attackDamage = entity.attackDamage,
                attackDamagePerLevel = entity.attackDamagePerLevel,
                armor = entity.armor,
                armorPerLevel = entity.armorPerLevel,
                spellBlock = entity.spellBlock,
                spellBlockPerLevel = entity.spellBlockPerLevel,
            )
        }

        operator fun invoke(data: ChampData): ChampionsDataModel {
            return ChampionsDataModel(
                key = data.key.toInt(),
                id = data.id,
                name = data.name,
                title = data.title,
                tags = data.tags[0],
                blurb = data.blurb,
                hp = data.stats.hp,
                hpPerLevel = data.stats.hpPerLevel,
                moveSpeed = data.stats.moveSpeed,
                attackDamage = data.stats.attackDamage,
                attackDamagePerLevel = data.stats.attackDamagePerLevel,
                armor = data.stats.armor,
                armorPerLevel = data.stats.armorPerLevel,
                spellBlock = data.stats.spellBlock,
                spellBlockPerLevel = data.stats.spellBlockPerLevel,
            )
        }
    }
}
