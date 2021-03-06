package com.bombadu.bitcoinexchange.network

import com.bombadu.bitcoinexchange.local.ExchangeEntity
import com.bombadu.bitcoinexchange.local.LocalCoinListData
import com.bombadu.bitcoinexchange.local.LocalTickerData

object NetworkUtil {

    fun convertExchangeApiData(exchangeData: ExchangeData): List<ExchangeEntity> {
        val myList = mutableListOf<ExchangeEntity>()

        for (i in exchangeData.indices) {
            val name = exchangeData[i].name
            val established = exchangeData[i].yearEstablished
            val country = exchangeData[i].country
            val description = exchangeData[i].description
            val url = exchangeData[i].url
            val imageUrl = exchangeData[i].image
            val hasTradingIncentive = exchangeData[i].hasTradingIncentive
            val trustScore = exchangeData[i].trustScore
            val trustRank = exchangeData[i].trustScoreRank
            val tradeVolume = exchangeData[i].tradeVolume24hBtc

            myList.add(
                ExchangeEntity(
                    name,
                    established.toString(),
                    country,
                    description,
                    url,
                    imageUrl,
                    hasTradingIncentive,
                    trustScore,
                    trustRank,
                    tradeVolume
                )
            )
        }

        return myList
    }
    
    fun convertCoinListData(coinListData: List<CoinListData.CoinListDataItem>) : List<LocalCoinListData> {
        
        val myList = mutableListOf<LocalCoinListData>()
        
        for (i in coinListData.indices) {
            val id = coinListData[i].id
            val name = coinListData[i].name
            val symbol = coinListData[i].symbol
            
            myList.add(LocalCoinListData(id, name, symbol))
        }
        
        return myList
        
    }

    fun convertTickerData(nomicsTickerData: List<NomicsTickerData.NomicsTickerDataItem>): List<LocalTickerData> {
        val myList = mutableListOf<LocalTickerData>()

        for (i in nomicsTickerData.indices) {
            val id = nomicsTickerData[i].id
            val name = nomicsTickerData[i].name
            val price = nomicsTickerData[i].price
            val logoUrl = nomicsTickerData[i].logoUrl
            val change = nomicsTickerData[i].d.priceChange.toDouble()
            val changePct = nomicsTickerData[i].d.priceChangePct
            val symbol = nomicsTickerData[i].symbol
            val status = nomicsTickerData[i].status
            val timeStamp = nomicsTickerData[i].priceTimestamp
            val circulatingSupply = nomicsTickerData[i].circulatingSupply
            val maxSupply = nomicsTickerData[i].maxSupply
            val marketCap = nomicsTickerData[i].marketCap
            val rank = nomicsTickerData[i].rank
            val high = nomicsTickerData[i].high
            val highTimeStamp = nomicsTickerData[i].highTimestamp
            val volume = nomicsTickerData[i].d.volume
            val priceChange = nomicsTickerData[i].d.priceChange
            val volumeChangePct = nomicsTickerData[i].d.volumeChangePct
            val marketCapChange = nomicsTickerData[i].d.marketCapChange
            val marketCapChangePct = nomicsTickerData[i].d.marketCapChangePct

            myList.add(LocalTickerData(id, name, price, logoUrl, change, changePct, symbol, status,
            timeStamp, circulatingSupply/*, maxSupply*/, marketCap, rank, high, highTimeStamp,
            volume, priceChange, volumeChangePct, marketCapChange, marketCapChangePct))
        }

        return myList
    }




}