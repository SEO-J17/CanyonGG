package io.github.seoj17.data.model

import io.github.seoj17.data.remote.response.match.Info
import io.github.seoj17.data.remote.response.match.MatchInfoResponse
import io.github.seoj17.data.remote.response.match.Metadata

data class MatchDataModel(
    val info: Info,
    val metadata: Metadata,
) {
    companion object {
        operator fun invoke(response: MatchInfoResponse): MatchDataModel {
            return MatchDataModel(
                info = response.info,
                metadata = response.metadata,
            )
        }
    }
}
