package io.github.seoj17.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.seoj17.data.local.match.MatchInfoDao
import io.github.seoj17.data.local.match.MatchInfoEntity
import io.github.seoj17.data.model.MatchInfoDataModel
import io.github.seoj17.data.remote.match.MatchesService

class MatchPagingSource(
    private val matchRemoteService: MatchesService,
    private val localService: MatchInfoDao,
    private val summonerPuuid: String,
    private val networkPageSize: Int,
) : PagingSource<Int, MatchInfoDataModel>() {
    override fun getRefreshKey(state: PagingState<Int, MatchInfoDataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(networkPageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(networkPageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchInfoDataModel> {
        val startId = params.key ?: STARTING_ID
        val matchesIds = matchRemoteService.getMatchId(summonerPuuid, startId)
        val matchInfo = matchesIds.map {
            matchRemoteService.getMatchInfo(it)
        }

        matchInfo.forEach { response ->
            localService.insert(
                MatchInfoEntity(
                    response.info.participants,
                    response.info.gameCreation,
                    response.metadata.matchId,
                    response.info.gameMode,
                ),
            )
        }

        return LoadResult.Page(
            data = MatchInfoDataModel(localService.getMatchInfo(summonerPuuid)),
            prevKey = if (startId == STARTING_ID) null else startId - networkPageSize,
            nextKey = if (matchesIds.size < params.loadSize) null else startId + networkPageSize,
        )
    }

    companion object {
        private const val STARTING_ID = 0
    }
}
