package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionsRepository
import io.github.seoj17.domain.model.ChampionsDomainModel
import io.github.seoj17.domain.usecase.bookmark.GetBookmarkChampionUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@Reusable
class GetAllChampionUseCase @Inject constructor(
    private val championsRepository: ChampionsRepository,
    private val getBookmarkChampionUseCase: GetBookmarkChampionUseCase,
) {
    operator fun invoke(): Flow<List<ChampionsDomainModel>> {
        val bookmarkChampFlow = getBookmarkChampionUseCase()
        val championFlow = championsRepository.getAllChampion()

        return bookmarkChampFlow.combine(championFlow) { bookmarkChampList, championList ->
            championList.map { champ ->
                val isBookmark = bookmarkChampList.any { it.key == champ.key }
                ChampionsDomainModel(champ, isBookmark)
            }
        }
    }
}
