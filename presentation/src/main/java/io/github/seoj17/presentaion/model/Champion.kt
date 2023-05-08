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
            )
        }
    }
}
