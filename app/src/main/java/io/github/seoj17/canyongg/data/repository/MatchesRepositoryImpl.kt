package io.github.seoj17.canyongg.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.github.seoj17.canyongg.data.MatchPagingSource
import io.github.seoj17.canyongg.data.local.match.MatchInfoDao
import io.github.seoj17.canyongg.data.local.match.MatchInfoEntity
import io.github.seoj17.canyongg.data.model.DataMatches
import io.github.seoj17.canyongg.data.model.MatchInfo
import io.github.seoj17.canyongg.data.remote.MatchesService
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val matchRemoteService: MatchesService,
    private val matchInfoService: MatchInfoDao,
) : MatchesRepository {

    override fun getMatches(puuid: String): Pager<Int, DataMatches> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MatchPagingSource(matchRemoteService, matchInfoService, puuid, NETWORK_PAGE_SIZE)
            }
        )
    }

    override suspend fun getMatchId(puuid: String, startIndex: Int): List<String> {
        return matchRemoteService.getMatchId(puuid, startIndex)
    }

    override suspend fun getMatchInfo(puuid: String, startIndex: Int): List<MatchInfo> {
        return getMatchId(puuid, startIndex).map { id ->
            MatchInfo(matchRemoteService.getMatchInfo(id))
        }
    }

    override suspend fun getMatchInfo(matchId: String): MatchInfo {
        return MatchInfo(matchRemoteService.getMatchInfo(matchId))
    }

    override suspend fun getMyMatchInfo(puuid: String): List<MatchInfoEntity> {
        return matchInfoService.getMyMatchInfo(puuid)
    }

    override suspend fun getParticipantsMatchInfo(matchId: String): List<MatchInfoEntity> {
        return matchInfoService.getParticipantsMatchInfo(matchId)
    }

    override suspend fun addMatchInfo(entity: MatchInfoEntity) {
        matchInfoService.insert(entity)
    }

    override suspend fun addMatchInfo(entities: List<MatchInfoEntity>) {
        matchInfoService.insert(entities)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}