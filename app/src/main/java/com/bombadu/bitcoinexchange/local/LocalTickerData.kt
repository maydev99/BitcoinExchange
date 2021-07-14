package com.bombadu.bitcoinexchange.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalTickerData(
    val id: String,
    val name: String,
    val price: String,
    val logoUrl: String,
    val change: Double,
    val changePct: String,
    val symbol: String,
    val status: String,
    val timeStamp: String,
    val circulating_supply: String,
    /*val maxSupply: String,*/
    val marketCap: String,
    val rank: String,
    val high: String,
    val high_timeStamp: String,
    val volume: String,
    val price_change: String,
    val volume_change_pct: String,
    val market_cap_change: String,
    val market_cap_change_pct: String
) : Parcelable
