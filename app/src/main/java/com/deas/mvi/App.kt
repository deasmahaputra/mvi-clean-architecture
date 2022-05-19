package com.deas.mvi

import android.app.Application
import com.deas.mylibrary.common.navigation.homeScreenNavModule
import com.deas.mylibrary.common.navigation.detailScreenNavModule
import com.deas.navigation.base.multiNavigationModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@HiltAndroidApp
@Module
@InstallIn(SingletonComponent::class)
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        multiNavigationModule(
            homeScreenNavModule,
            detailScreenNavModule
        )
    }

}