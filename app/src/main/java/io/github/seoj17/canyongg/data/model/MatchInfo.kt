package io.github.seoj17.canyongg.data.model

import io.github.seoj17.canyongg.data.remote.response.Info
import io.github.seoj17.canyongg.data.remote.response.MatchInfoResponse
import io.github.seoj17.canyongg.data.remote.response.Metadata

data class MatchInfo(
    val info: Info,
    val metadata: Metadata,
) {
    companion object {
        operator fun invoke(response: MatchInfoResponse): MatchInfo {
            return MatchInfo(
                info = response.info,
                metadata = response.metadata,
            )
        }
    }
}