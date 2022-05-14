package com.deas.mvi.di

import com.deas.data.repository.LocalDataSource
import com.deas.local.source.LocalDataSourceImpl
import com.deas.remote.source.RemoteDataSourceImpl
import com.deas.data.repository.CategoryRepositoryImpl
import com.deas.data.repository.RemoteDataSource
import com.deas.domain.repository.Repository
import com.deas.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: RemoteDataSourceImpl): RemoteDataSource = apiHelper

    @Singleton
    @Provides
    fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl) : LocalDataSource = localDataSourceImpl

    @Singleton
    @Provides
    fun provideUserRepository(
        userRepositoryImpl: CategoryRepositoryImpl
    ): Repository = userRepositoryImpl
}