package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.remote.response.perks.PerksResponse
import io.github.seoj17.canyongg.data.remote.response.perks.Rune

data class DataPerks(
    var id: Int,
    var name: String,
    var imgUrl: String
) {
    companion object {
        operator fun invoke(response: PerksResponse): DataPerks {
            return DataPerks(
                id = response.id,
                name = response.key,
                imgUrl = response.icon,
            )
        }

        @JvmName("responseToDataPerks")
        operator fun invoke(response: List<PerksResponse>): List<DataPerks> {
            return response.map {
                invoke(it)
            }
        }

        operator fun invoke(runes: Rune): DataPerks {
            return DataPerks(
                id = runes.id,
                name = runes.key,
                imgUrl = runes.icon,
            )
        }
        @JvmName("runesToDataPerks")
        operator fun invoke(runes: List<Rune>): List<DataPerks> {
            return runes.map {
                invoke(it)
            }
        }
    }
}