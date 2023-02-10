package io.github.seoj17.canyongg.data.repository

import androidx.paging.Pager
import io.github.seoj17.canyongg.data.model.DataMatches
import io.github.seoj17.canyongg.data.model.MatchInfo

interface MatchesRepository {
    fun getMatches(puuid: String): Pager<Int, DataMatches>
    suspend fun getMatchId(puuid: String, startIndex: Int = 0): List<String>
    suspend fun getMatchInfo(puuid: String, startIndex: Int = 0): List<MatchInfo>
}