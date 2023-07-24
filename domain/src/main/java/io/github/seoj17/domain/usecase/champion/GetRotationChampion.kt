package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionsRepository
import io.github.seoj17.domain.model.RotationChampionDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetRotationChampion @Inject constructor(
    private val getRotationChampIdListUseCase: GetRotationChampIdListUseCase,
    private val repository: ChampionsRepository,
) {
    operator fun invoke(): Flow<List<RotationChampionDomainModel>> {
        return flow {
            emit(
                getRotationChampIdListUseCase().mapNotNull { id ->
                    repository
                        .getChampion(id)
                        ?.let {
                            RotationChampionDomainModel(it)
                        }
                },
            )
        }
    }
}
