package com.bombadu.bitcoinexchange.di

import com.bombadu.bitcoinexchange.local.LocalDatabase
import com.bombadu.bitcoinexchange.network.CoinGeckoApi
import com.bombadu.bitcoinexchange.repository.ExchangeRepository
import com.bombadu.bitcoinexchange.util.Constants.Companion.BASE_URL
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

    @Singleton
    @Provides
    fun provideCoinGeckoApi() : CoinGeckoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideExchangeRepository(
        database: LocalDatabase
    ) = ExchangeRepository(database)
}