package io.github.seoj17.canyongg.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.github.seoj17.canyongg.data.MatchPagingSource
import io.github.seoj17.canyongg.data.model.DataMatches
import io.github.seoj17.canyongg.data.model.MatchInfo
import io.github.seoj17.canyongg.data.remote.MatchesService
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val matchRemoteService: MatchesService,
) : MatchesRepository {

    override fun getMatches(puuid: String): Pager<Int, DataMatches> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MatchPagingSource(matchRemoteService, puuid, NETWORK_PAGE_SIZE)
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

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}