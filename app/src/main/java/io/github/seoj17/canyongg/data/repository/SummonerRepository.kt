package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.model.SummonerTier

interface SummonerRepository {
    suspend fun getSummonerInfo(userName: String): Summoner?
    suspend fun getTier(id:String) : SummonerTier?
}