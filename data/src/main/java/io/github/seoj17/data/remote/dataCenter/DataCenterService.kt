package io.github.seoj17.data.remote.dataCenter

import io.github.seoj17.data.remote.response.champion.ChampResponse
import io.github.seoj17.data.remote.response.perks.PerksResponse
import io.github.seoj17.data.remote.response.spell.SpellResponse
import javax.inject.Inject

class DataCenterService @Inject constructor(
    private val dataCenterApi: DataCenterApi,
) {
    suspend fun getSpells(): SpellResponse = dataCenterApi.getSpellInfo()
    
    suspend fun getPerks(): List<PerksResponse> = dataCenterApi.getPerksInfo()
    
    suspend fun getChamps(): ChampResponse = dataCenterApi.getChampInfo()
}
