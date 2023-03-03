package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.perks.PerkEntity
import io.github.seoj17.canyongg.data.model.PerkDataModel

data class PerkDomainModel(
    var id: Int,
    var name: String,
    var imgUrl: String,
) {
    companion object {
        operator fun invoke(data: PerkDataModel): PerkDomainModel {
            return PerkDomainModel(
                id = data.id,
                name = data.name,
                imgUrl = data.imgUrl,
            )
        }

        operator fun invoke(entity: PerkEntity): PerkDomainModel {
            return PerkDomainModel(
                id = entity.id,
                name = entity.name,
                imgUrl = entity.imgUrl,
            )
        }

        fun toEntity(domain: PerkDomainModel): PerkEntity {
            return PerkEntity(
                id = domain.id,
                name = domain.name,
                imgUrl = domain.imgUrl,
            )
        }

        fun toEntity(domain: List<PerkDomainModel>): List<PerkEntity> {
            return domain.map {
                toEntity(it)
            }
        }
    }
}
