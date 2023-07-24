package io.github.seoj17.domain.model

import io.github.seoj17.data.model.ChampionsDataModel

data class RotationChampionDomainModel(
    val key: Int,
    val id: String,
    val name: String,
) {
    companion object {
        operator fun invoke(data: ChampionsDataModel): RotationChampionDomainModel {
            return RotationChampionDomainModel(
                key = data.key,
                id = data.id,
                name = data.name,
            )
        }
    }
}
