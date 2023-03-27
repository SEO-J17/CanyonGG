package io.github.seoj17.domain.model

import io.github.seoj17.data.model.MatchDataModel
import io.github.seoj17.data.remote.response.match.Info
import io.github.seoj17.data.remote.response.match.Metadata

data class MatchDomainModel(
    val info: Info,
    val metadata: Metadata,
) {
    companion object {
        operator fun invoke(data: MatchDataModel): MatchDomainModel {
            return MatchDomainModel(
                info = data.info,
                metadata = data.metadata,
            )
        }
    }
}
