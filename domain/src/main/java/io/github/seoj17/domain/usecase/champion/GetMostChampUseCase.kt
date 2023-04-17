package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.mostchamp.MostChampRepository
import io.github.seoj17.domain.model.MostChampsDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetMostChampUseCase @Inject constructor(
    private val repository: MostChampRepository,
) {
    operator fun invoke(): Flow<List<MostChampsDomainModel>> {
        return repository
            .getMyMostChamps()
            .map {
                MostChampsDomainModel(it)
            }
    }
}
