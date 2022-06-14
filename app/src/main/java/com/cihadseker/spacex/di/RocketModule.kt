package com.cihadseker.spacex.di

import com.cihadseker.spacex.data.repository.SpaceXService
import com.cihadseker.spacex.data.repository.SpaceXRepository
import com.cihadseker.spacex.data.repository.SpaceXRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RocketModule {

    @Provides
    @Singleton
    fun provideSpaceXRocketListService(retrofit: Retrofit): SpaceXService = retrofit.create(SpaceXService::class.java)

    @Provides
    @Singleton
    fun provideSpaceXRocketListRepository(
        service: SpaceXService,
    ): SpaceXRepository = SpaceXRepositoryImpl(service)

}