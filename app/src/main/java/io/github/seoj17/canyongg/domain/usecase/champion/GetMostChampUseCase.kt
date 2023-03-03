package io.github.seoj17.canyongg.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.RegisterUserRepository
import io.github.seoj17.canyongg.domain.model.MostChampsDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetMostChampUseCase @Inject constructor(
    private val repository: RegisterUserRepository,
) {
    operator fun invoke(): Flow<List<MostChampsDomainModel>> {
        return repository
            .getMyMostChamps()
            .map {
                MostChampsDomainModel(it)
            }
    }
}