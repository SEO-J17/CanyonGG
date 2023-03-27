package io.github.seoj17.data.repository

import androidx.paging.Pager
import io.github.seoj17.data.local.match.MatchInfoEntity
import io.github.seoj17.data.model.MatchDataModel
import io.github.seoj17.data.model.MatchInfoDataModel

interface MatchRepository {
    fun getMatches(puuid: String): Pager<Int, MatchInfoDataModel>
    suspend fun getMatchId(puuid: String, startIndex: Int = 0): List<String>
    suspend fun getMatchInfo(matchId: String): MatchDataModel
    suspend fun getRegisterUserMatchInfo(puuid: String): List<MatchInfoDataModel>
    suspend fun getParticipantsMatchInfo(matchId: String): List<MatchInfoDataModel>
    suspend fun addMatchInfo(entity: MatchInfoEntity)
    suspend fun addMatchInfo(entities: List<MatchInfoEntity>)
}
