package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.local.perks.PerkEntity
import io.github.seoj17.canyongg.data.remote.response.perks.PerksResponse
import io.github.seoj17.canyongg.data.remote.response.perks.Rune

data class PerkDataModel(
    var id: Int,
    var name: String,
    var imgUrl: String,
) {
    companion object {
        operator fun invoke(response: PerksResponse): PerkDataModel {
            return PerkDataModel(
                id = response.id,
                name = response.key,
                imgUrl = response.icon,
            )
        }

        operator fun invoke(entity: PerkEntity): PerkDataModel {
            return PerkDataModel(
                id = entity.id,
                name = entity.name,
                imgUrl = entity.imgUrl,
            )
        }

        @JvmName("responseToDataPerks")
        operator fun invoke(response: List<PerksResponse>): List<PerkDataModel> {
            return response.map {
                invoke(it)
            }
        }

        operator fun invoke(runes: Rune): PerkDataModel {
            return PerkDataModel(
                id = runes.id,
                name = runes.key,
                imgUrl = runes.icon,
            )
        }

        @JvmName("runesToDataPerks")
        operator fun invoke(runes: List<Rune>): List<PerkDataModel> {
            return runes.map {
                invoke(it)
            }
        }
    }
}