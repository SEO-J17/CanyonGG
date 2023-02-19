package io.github.seoj17.canyongg.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.canyongg.data.repository.ChampionsRepository
import io.github.seoj17.canyongg.data.repository.ChampionsRepositoryImpl
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.MatchesRepository
import io.github.seoj17.canyongg.data.repository.MatchesRepositoryImpl
import io.github.seoj17.canyongg.data.repository.SummonerRepository
import io.github.seoj17.canyongg.data.repository.SummonerRepositoryImpl
import io.github.seoj17.canyongg.data.repository.DataCenterRepositoryImpl
import io.github.seoj17.canyongg.data.repository.PerksRepository
import io.github.seoj17.canyongg.data.repository.PerksRepositoryImpl
import io.github.seoj17.canyongg.data.repository.SummonerBookmarkRepository
import io.github.seoj17.canyongg.data.repository.SummonerBookmarkRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindSummonerRepository(summonerRepositoryImpl: SummonerRepositoryImpl): SummonerRepository

    @Binds
    abstract fun bindMatchesRepository(matchesRepositoryImpl: MatchesRepositoryImpl): MatchesRepository

    @Binds
    abstract fun bindDataCenterRepository(dataCenterRepositoryImpl: DataCenterRepositoryImpl): DataCenterRepository

    @Binds
    abstract fun bindSummonerBookmarkRepository(summonerBookmarkRepositoryImpl: SummonerBookmarkRepositoryImpl): SummonerBookmarkRepository

    @Binds
    abstract fun bindChampionsRepository(championsRepositoryImpl: ChampionsRepositoryImpl): ChampionsRepository

    @Binds
    abstract fun bindPerksRepository(perksRepositoryImpl: PerksRepositoryImpl): PerksRepository

}