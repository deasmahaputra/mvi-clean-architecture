package com.deas.mvi.di

import android.content.Context
import androidx.room.Room
import com.deas.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "database")
            .build()
    }


    @Provides
    @Singleton
    fun provideWeatherDAO(appDatabase: AppDatabase) = appDatabase.roomDao()
}