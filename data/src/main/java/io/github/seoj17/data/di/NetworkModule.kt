package io.github.seoj17.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.data.BuildConfig
import io.github.seoj17.data.remote.dataCenter.DataCenterApi
import io.github.seoj17.data.remote.match.MatchesApi
import io.github.seoj17.data.remote.summoner.SummonerApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    @TimeOutPolicy
    fun provideConnectTimeoutPolicy(): Long {
        return 10_000L
    }

    @Singleton
    @Provides
    @SummonerUrl
    fun provideSummonerUrl(): String {
        return "https://kr.api.riotgames.com"
    }

    @Singleton
    @Provides
    @MatchUrl
    fun provideMatchUrl(): String {
        return "https://asia.api.riotgames.com"
    }

    @Singleton
    @Provides
    @DataCenterUrl
    fun provideDataCenterUrl(): String {
        return "https://ddragon.leagueoflegends.com"
    }

    @Singleton
    @Provides
    @AuthToken
    fun provideAuthToken(): String {
        return BuildConfig.RIOT_API_KEY
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(@AuthToken authToken: String): Interceptor {
        return Interceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("X-Riot-Token", authToken)
                    .build(),
            )
        }
    }

    @Singleton
    @Provides
    fun provideClient(
        authInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @TimeOutPolicy connectTimeoutPolicy: Long,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(
                connectTimeoutPolicy,
                TimeUnit.MILLISECONDS,
            )
            .build()
    }

    @Singleton
    @Provides
    @SummonerRetrofit
    fun provideSummonerRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @SummonerUrl url: String,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @MatchRetrofit
    fun provideMatchRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @MatchUrl url: String,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @DataCenterRetrofit
    fun provideDataCenterRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @DataCenterUrl url: String,
        cdnVersion: String,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("$url/cdn/$cdnVersion/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideSummonerService(@SummonerRetrofit retrofit: Retrofit): SummonerApi {
        return retrofit.create(SummonerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMatchService(@MatchRetrofit retrofit: Retrofit): MatchesApi {
        return retrofit.create(MatchesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDataCenterService(@DataCenterRetrofit retrofit: Retrofit): DataCenterApi {
        return retrofit.create(DataCenterApi::class.java)
    }
}
