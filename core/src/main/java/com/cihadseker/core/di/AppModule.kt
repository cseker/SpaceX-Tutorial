package com.cihadseker.core.di

import com.cihadseker.core.base.CacheManager
import com.cihadseker.core.util.GsonProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson() = GsonProvider.gson

    @Singleton
    @Provides
    fun provideCacheManager() = CacheManager()
}
