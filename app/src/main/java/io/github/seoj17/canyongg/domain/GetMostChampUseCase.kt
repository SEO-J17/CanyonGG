package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.MyUserRepository
import io.github.seoj17.canyongg.domain.model.DomainMostChamps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetMostChampUseCase @Inject constructor(
    private val repository: MyUserRepository
) {
    operator fun invoke(): Flow<List<DomainMostChamps>> {
        return repository
            .getMyMostChamps().map {
                DomainMostChamps(it)
            }
    }
}