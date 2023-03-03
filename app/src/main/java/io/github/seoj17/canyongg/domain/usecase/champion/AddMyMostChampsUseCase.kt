package io.github.seoj17.canyongg.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.RegisterUserRepository
import io.github.seoj17.canyongg.domain.model.MostChampsDomainModel
import javax.inject.Inject

@Reusable
class AddMyMostChampsUseCase @Inject constructor(
    private val repository: RegisterUserRepository,
) {
    suspend operator fun invoke(domain: List<MostChampsDomainModel>) {
        repository.addMostChamps(
            MostChampsDomainModel.toEntity(domain)
        )
    }
}