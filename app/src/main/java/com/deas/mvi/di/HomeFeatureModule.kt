package com.deas.mvi.di

import com.deas.data.repository.LocalDataSource
import com.deas.local.source.LocalDataSourceImpl
import com.deas.mylibrary.common.api.HomeApiHelper
import com.deas.mylibrary.common.api.HomeApiHelperImpl
import com.deas.mylibrary.common.api.HomeApiService
import com.deas.mylibrary.domain.repository.CategoryRepository
import com.deas.mylibrary.domain.repository.CategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeFeatureModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): HomeApiService = retrofit.create(HomeApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: HomeApiHelperImpl): HomeApiHelper = apiHelper

    @Singleton
    @Provides
    fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl) : LocalDataSource = localDataSourceImpl

    @Singleton
    @Provides
    fun provideUserRepository(
        userRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository = userRepositoryImpl
}