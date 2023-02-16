package io.github.seoj17.canyongg.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.seoj17.canyongg.data.model.DataMatches
import io.github.seoj17.canyongg.data.remote.MatchesService

class MatchPagingSource(
    private val matchRemoteService: MatchesService,
    private val summonerPuuid: String,
    private val networkPageSize: Int,
) : PagingSource<Int, DataMatches>() {
    override fun getRefreshKey(state: PagingState<Int, DataMatches>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(networkPageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(networkPageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataMatches> {
        val startId = params.key ?: STARTING_ID
        val matchesIds = matchRemoteService.getMatchId(summonerPuuid, startId)
        val matchInfo = matchesIds.map {
            matchRemoteService.getMatchInfo(it)
        }

        return LoadResult.Page(
            data = DataMatches(matchInfo, summonerPuuid),
            prevKey = if (startId == STARTING_ID) null else startId - networkPageSize,
            nextKey = if (matchesIds.size < params.loadSize) null else startId + networkPageSize
        )
    }

    companion object {
        private const val STARTING_ID = 0
    }
}