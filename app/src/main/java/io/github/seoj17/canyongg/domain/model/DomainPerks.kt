package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.local.perks.PerksEntity
import io.github.seoj17.canyongg.data.model.DataPerks

data class DomainPerks(
    var id: Int,
    var name: String,
    var imgUrl: String,
) {
    companion object {
        operator fun invoke(data: DataPerks): DomainPerks {
            return DomainPerks(
                id = data.id,
                name = data.name,
                imgUrl = data.imgUrl,
            )
        }

        operator fun invoke(entity: PerksEntity): DomainPerks {
            return DomainPerks(
                id = entity.id,
                name = entity.name,
                imgUrl = entity.imgUrl,
            )
        }
    }
}
