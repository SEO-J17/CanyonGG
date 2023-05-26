package io.github.seoj17.presentaion.model

import androidx.recyclerview.widget.DiffUtil
import io.github.seoj17.domain.model.ChampionsDomainModel

data class Champion(
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
    val isBookmark: Boolean,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Champion>() {
            override fun areItemsTheSame(
                oldItem: Champion,
                newItem: Champion,
            ): Boolean {
                return oldItem.key == newItem.key
            }

            override fun areContentsTheSame(
                oldItem: Champion,
                newItem: Champion,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: ChampionsDomainModel): Champion {
            return Champion(
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
                isBookmark = domain.isBookMark,
            )
        }

        operator fun invoke(list: List<ChampionsDomainModel>): List<Champion> {
            return list.map {
                invoke(it)
            }
        }

        fun toDomain(champion: Champion): ChampionsDomainModel {
            return ChampionsDomainModel(
                key = champion.key,
                id = champion.id,
                name = champion.name,
                title = champion.title,
                tag = champion.tag,
                blurb = champion.blurb,
                hp = champion.hp,
                hpPerLevel = champion.hpPerLevel,
                moveSpeed = champion.moveSpeed,
                attackDamage = champion.attackDamage,
                attackDamagePerLevel = champion.attackDamagePerLevel,
                armor = champion.armor,
                armorPerLevel = champion.armorPerLevel,
                spellBlock = champion.spellBlock,
                spellBlockPerLevel = champion.spellBlockPerLevel,
                isBookMark = champion.isBookmark,
            )
        }
    }
}
