package com.bombadu.bitcoinexchange.ui.coins

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bombadu.bitcoinexchange.BuildConfig
import com.bombadu.bitcoinexchange.local.LocalCoinListData
import com.bombadu.bitcoinexchange.local.LocalTickerData
import com.bombadu.bitcoinexchange.network.Network
import com.bombadu.bitcoinexchange.network.NetworkUtil
import com.bombadu.bitcoinexchange.network.NomicsNetwork
import kotlinx.coroutines.launch

class CoinsViewModel(application: Application) : AndroidViewModel(application) {

   /* val myCoinData: LiveData<List<LocalCoinListData>>
        get() = _myCoinData

    private val _myCoinData = MutableLiveData<List<LocalCoinListData>>()*/

    val apiKey = BuildConfig.API_KEY



    val myTickerData: LiveData<List<LocalTickerData>>
    get() = _myTickerData

    private val _myTickerData = MutableLiveData<List<LocalTickerData>>()

    private val tickerList = listOf("BTC, ETH, XRP, DOGE, LTC, LINK, TRX, ADA, BNB, ETC, HEX")




    init {
        //getCoinList()
        getTickerData()
    }

    private fun getTickerData() {
        viewModelScope.launch {
            try{
                val networkData = NomicsNetwork.nomApi.getTickerData(
                    apiKey, tickerList, "1d", "USD", "100", "1")
                _myTickerData.value = NetworkUtil.convertTickerData(networkData)

            } catch (e: Exception) {
                Log.e(TAG, "No Nomics Data")
            }
        }
    }

    private fun getCoinList() {
        viewModelScope.launch {

            try {
                val networkData = Network.api.getCoinList()
               // _myCoinData.value = NetworkUtil.convertCoinListData(networkData)

            } catch (e: Exception) {
                Log.e(TAG, "No Data")
            }


        }
    }



    companion object {
        val TAG = CoinsViewModel::class.java.simpleName
    }

}