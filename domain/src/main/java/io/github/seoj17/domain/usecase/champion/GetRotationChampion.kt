package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetRotationChampion @Inject constructor(
    private val getRotationChampIdListUseCase: GetRotationChampIdListUseCase,
    private val getChampionNameUseCase: GetChampionNameUseCase,
) {
    operator fun invoke(): Flow<List<String>> {
        return flow {
            emit(
                getChampionNameUseCase(getRotationChampIdListUseCase()),
            )
        }
    }
}
