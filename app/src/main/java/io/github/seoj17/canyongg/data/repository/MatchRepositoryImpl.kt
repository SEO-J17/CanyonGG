package io.github.seoj17.canyongg.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.github.seoj17.canyongg.data.MatchPagingSource
import io.github.seoj17.canyongg.data.local.match.MatchInfoDao
import io.github.seoj17.canyongg.data.local.match.MatchInfoEntity
import io.github.seoj17.canyongg.data.model.MatchDataModel
import io.github.seoj17.canyongg.data.model.MatchInfoDataModel
import io.github.seoj17.canyongg.data.remote.MatchesService
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val matchRemoteService: MatchesService,
    private val matchInfoService: MatchInfoDao,
) : MatchRepository {

    override fun getMatches(puuid: String): Pager<Int, MatchInfoDataModel> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                MatchPagingSource(matchRemoteService, matchInfoService, puuid, NETWORK_PAGE_SIZE)
            },
        )
    }

    override suspend fun getMatchId(puuid: String, startIndex: Int): List<String> {
        return matchRemoteService.getMatchId(puuid, startIndex)
    }

    override suspend fun getMatchInfo(matchId: String): MatchDataModel {
        return MatchDataModel(matchRemoteService.getMatchInfo(matchId))
    }

    override suspend fun getRegisterUserMatchInfo(puuid: String): List<MatchInfoDataModel> {
        return matchInfoService
            .getMatchInfo(puuid)
            .map {
                MatchInfoDataModel(it)
            }
    }

    override suspend fun getParticipantsMatchInfo(matchId: String): List<MatchInfoDataModel> {
        return matchInfoService
            .getParticipantsMatchInfo(matchId)
            .map {
                MatchInfoDataModel(it)
            }
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
