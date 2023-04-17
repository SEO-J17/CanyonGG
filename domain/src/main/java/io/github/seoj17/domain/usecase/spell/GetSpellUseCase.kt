package io.github.seoj17.domain.usecase.spell

import dagger.Reusable
import io.github.seoj17.data.repository.datacenter.DataCenterRepository
import javax.inject.Inject

@Reusable
class GetSpellUseCase @Inject constructor(
    private val dataCenterRepository: DataCenterRepository,
) {
    suspend operator fun invoke(key: Int): String {
        return dataCenterRepository.getSpell(key)
    }
}
