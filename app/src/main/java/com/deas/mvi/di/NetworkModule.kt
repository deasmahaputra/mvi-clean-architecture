package com.deas.mvi.di

import com.deas.core.BuildConfig
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() : HttpLoggingInterceptor {
        val loggingLevel = HttpLoggingInterceptor.Level.BODY
        return HttpLoggingInterceptor().setLevel(loggingLevel)
    }

    @Provides
    @Singleton
    fun provideOkhttpProfilerInterceptor() : OkHttpProfilerInterceptor {
        return OkHttpProfilerInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        okHttpProfilerInterceptor: OkHttpProfilerInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30L, TimeUnit.SECONDS)
        .connectTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "")
                .build()
            chain.proceed(newRequest)
        }.addInterceptor(okHttpProfilerInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
}