package com.bombadu.bitcoinexchange.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bombadu.bitcoinexchange.local.ExchangeEntity
import com.bombadu.bitcoinexchange.local.LocalDatabase
import com.bombadu.bitcoinexchange.network.ExchangeData
import com.bombadu.bitcoinexchange.network.Network
import com.bombadu.bitcoinexchange.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExchangeRepository(private val database: LocalDatabase) {

    val xData: LiveData<List<ExchangeEntity>> = database.exchangeDao.getAllExchanges()


    suspend fun refreshExchangeData() {
        try {
            withContext(Dispatchers.IO) {
                val networkData = Network.api.getExchanges()
                val convertedData = NetworkUtil.convertExchangeApiData(networkData)
                for (i in convertedData.indices) {
                    database.exchangeDao.insertExchanges(convertedData[i])
                }

            }


        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.e(TAG, "Data request failed")
            }
        }
    }

    companion object {
        val TAG = ExchangeRepository::class.java.simpleName
    }
}