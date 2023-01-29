package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.model.MatchInfo
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.model.SummonerTier

interface InfoRepository {
    suspend fun getSummonerInfo(userName: String): Summoner?
    suspend fun getMatchInfo(puuid: String, startIndex: Int): List<MatchInfo>
    suspend fun getTier(id:String) : SummonerTier?
}