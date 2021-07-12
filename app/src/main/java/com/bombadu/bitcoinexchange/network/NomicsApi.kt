package com.bombadu.bitcoinexchange.network

import com.bombadu.bitcoinexchange.BuildConfig
import com.bombadu.bitcoinexchange.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface NomicsApi {

    //https://nomics.com/docs/#operation/getCurrenciesTicker

    @GET("v1/currencies/ticker?")
    suspend fun getTickerData(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("ids") symbols: List<String>, //[BTC,ETH,XRP]
        @Query("interval") interval: String, //1d
        @Query("convert") convert: String, //USD
        @Query("per_page") perPage: String, //100
        @Query("page") page: String //1

    ) : NomicsTickerData


}

object NomicsNetwork {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.NOMICS_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val nomApi: NomicsApi by lazy {
        retrofit.create(NomicsApi::class.java)
    }
}