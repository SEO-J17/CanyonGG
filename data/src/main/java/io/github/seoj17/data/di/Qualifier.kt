package io.github.seoj17.data.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SummonerUrl

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MatchUrl

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DataCenterUrl

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TimeOutPolicy

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AuthToken

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SummonerRetrofit

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MatchRetrofit

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DataCenterRetrofit
