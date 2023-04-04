package io.github.seoj17.data.model

import io.github.seoj17.data.local.champions.ChampionsEntity

data class ChampionsDataModel(
    val key: Int,
    val name: String,
) {
    companion object {
        operator fun invoke(entity: ChampionsEntity): ChampionsDataModel {
            return ChampionsDataModel(
                key = entity.key,
                name = entity.name,
            )
        }
    }
}
