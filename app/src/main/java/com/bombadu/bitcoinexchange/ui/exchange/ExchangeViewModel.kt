package com.bombadu.bitcoinexchange.ui.exchange

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bombadu.bitcoinexchange.local.ExchangeEntity
import com.bombadu.bitcoinexchange.local.getDatabase
import com.bombadu.bitcoinexchange.network.Network
import com.bombadu.bitcoinexchange.network.NetworkUtil
import com.bombadu.bitcoinexchange.repository.ExchangeRepository
import kotlinx.coroutines.launch

class ExchangeViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ExchangeRepository(getDatabase(application))
    val exchangeData = repository.xData


    fun refreshData() {
        viewModelScope.launch {
            repository.refreshExchangeData()
        }
    }


    companion object {
        val TAG = ExchangeViewModel::class.java.simpleName
    }


}