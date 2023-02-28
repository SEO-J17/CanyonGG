package io.github.seoj17.canyongg.data.remote

import io.github.seoj17.canyongg.data.remote.response.champion.ChampResponse
import io.github.seoj17.canyongg.data.remote.response.perks.PerksResponse
import io.github.seoj17.canyongg.data.remote.response.spell.SpellResponse
import retrofit2.await
import javax.inject.Inject

class DataCenterService @Inject constructor(
    private val dataCenterResponse: DataCenterApi,
) {
    suspend fun getSpells(): SpellResponse {
        return runCatching {
            dataCenterResponse.getSpellInfo().await()
        }.fold(
            onSuccess = { spellInfo ->
                spellInfo
            },
            onFailure = {
                throw it
            },
        )
    }

    suspend fun getPerks(): List<PerksResponse> {
        return runCatching {
            dataCenterResponse.getPerksInfo().await()
        }.fold(
            onSuccess = { perks ->
                perks
            },
            onFailure = {
                emptyList()
            }
        )
    }

    suspend fun getChamps(): ChampResponse {
        return runCatching {
            dataCenterResponse.getChampInfo().await()
        }.fold(
            onSuccess = { champs ->
                champs
            },
            onFailure = {
                throw it
            }
        )
    }
}