package io.github.seoj17.domain.model

import io.github.seoj17.data.local.champions.ChampionsEntity
import io.github.seoj17.data.model.ChampionsDataModel

data class ChampionsDomainModel(
    val key: Int,
    val id: String,
    val name: String,
    val title: String,
    val tag: String,
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
    val isBookMark: Boolean,
) {
    companion object {
        operator fun invoke(
            data: ChampionsDataModel,
            isBookMark: Boolean = false,
        ): ChampionsDomainModel {
            return ChampionsDomainModel(
                key = data.key,
                id = data.id,
                name = data.name,
                title = data.title,
                tag = data.tags,
                blurb = data.blurb,
                hp = data.hp,
                hpPerLevel = data.hpPerLevel,
                moveSpeed = data.moveSpeed,
                attackDamage = data.attackDamage,
                attackDamagePerLevel = data.attackDamagePerLevel,
                armor = data.armor,
                armorPerLevel = data.armorPerLevel,
                spellBlock = data.spellBlock,
                spellBlockPerLevel = data.spellBlockPerLevel,
                isBookMark = isBookMark,
            )
        }

        operator fun invoke(list: List<ChampionsDataModel>): List<ChampionsDomainModel> {
            return list.map {
                invoke(it)
            }
        }

        fun toEntity(domain: ChampionsDomainModel): ChampionsEntity {
            return ChampionsEntity(
                key = domain.key,
                id = domain.id,
                name = domain.name,
                title = domain.title,
                tag = domain.tag,
                blurb = domain.blurb,
                hp = domain.hp,
                hpPerLevel = domain.hpPerLevel,
                moveSpeed = domain.moveSpeed,
                attackDamage = domain.attackDamage,
                attackDamagePerLevel = domain.attackDamagePerLevel,
                armor = domain.armor,
                armorPerLevel = domain.armorPerLevel,
                spellBlock = domain.spellBlock,
                spellBlockPerLevel = domain.spellBlockPerLevel,
            )
        }

        fun toEntity(domain: List<ChampionsDomainModel>): List<ChampionsEntity> {
            return domain.map {
                toEntity(it)
            }
        }
    }
}
