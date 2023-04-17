package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.mostchamp.MostChampRepository
import io.github.seoj17.domain.model.MostChampsDomainModel
import javax.inject.Inject

@Reusable
class AddMyMostChampsUseCase @Inject constructor(
    private val repository: MostChampRepository,
) {
    suspend operator fun invoke(domain: List<MostChampsDomainModel>) {
        repository.addMostChamps(
            MostChampsDomainModel.toEntity(domain),
        )
    }
}
