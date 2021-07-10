package com.bombadu.bitcoinexchange.network

import com.bombadu.bitcoinexchange.local.ExchangeEntity

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


}