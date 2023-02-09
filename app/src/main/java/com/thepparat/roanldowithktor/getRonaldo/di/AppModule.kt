package com.thepparat.roanldowithktor.getRonaldo.di

import com.thepparat.roanldowithktor.getRonaldo.data.api.RonaldoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRonaldoApi(): RonaldoApi {
        return Retrofit.Builder().baseUrl(RonaldoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RonaldoApi::class.java)
    }
}