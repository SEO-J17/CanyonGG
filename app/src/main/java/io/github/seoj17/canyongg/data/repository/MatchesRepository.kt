package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.model.MatchInfo

interface MatchesRepository {
    suspend fun getMatchInfo(puuid: String, startIndex: Int): List<MatchInfo>
}