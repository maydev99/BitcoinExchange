package com.bombadu.bitcoinexchange.network


import com.bombadu.bitcoinexchange.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface CoinGeckoApi {

    @GET("/api/v3/exchanges?per_page=18&page=1")
    suspend fun getExchanges() : ExchangeData

}

object Network {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
           level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: CoinGeckoApi by lazy {
        retrofit.create(CoinGeckoApi::class.java)
    }
}