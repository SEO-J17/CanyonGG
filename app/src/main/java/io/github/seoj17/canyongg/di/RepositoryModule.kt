package io.github.seoj17.canyongg.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.canyongg.data.repository.MatchesRepository
import io.github.seoj17.canyongg.data.repository.MatchesRepositoryImpl
import io.github.seoj17.canyongg.data.repository.SummonerRepository
import io.github.seoj17.canyongg.data.repository.SummonerRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindSummonerRepository(summonerRepositoryImpl: SummonerRepositoryImpl): SummonerRepository

    @Binds
    abstract fun bindMatchesRepository(matchesRepositoryImpl: MatchesRepositoryImpl): MatchesRepository
}