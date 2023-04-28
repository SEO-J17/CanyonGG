package io.github.seoj17.data.remote.dataCenter

import io.github.seoj17.data.remote.response.champion.ChampResponse
import io.github.seoj17.data.remote.response.perks.PerksResponse
import io.github.seoj17.data.remote.response.spell.SpellResponse
import retrofit2.await
import javax.inject.Inject

class DataCenterService @Inject constructor(
    private val dataCenterApi: DataCenterApi,
) {
    suspend fun getSpells(): SpellResponse? {
        return runCatching {
            dataCenterApi.getSpellInfo().await()
        }.fold(
            onSuccess = { spellInfo ->
                spellInfo
            },
            onFailure = {
                null
            },
        )
    }

    suspend fun getPerks(): List<PerksResponse> {
        return runCatching {
            dataCenterApi.getPerksInfo().await()
        }.fold(
            onSuccess = { perks ->
                perks
            },
            onFailure = {
                emptyList()
            },
        )
    }

    suspend fun getChamps(): ChampResponse? {
        return runCatching {
            dataCenterApi.getChampInfo().await()
        }.fold(
            onSuccess = { champs ->
                champs
            },
            onFailure = {
                null
            },
        )
    }
}
