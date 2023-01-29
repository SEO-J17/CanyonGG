package io.github.seoj17.canyongg.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.canyongg.data.repository.InfoRepository
import io.github.seoj17.canyongg.data.repository.InfoRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(infoRepositoryImpl: InfoRepositoryImpl): InfoRepository
}