package com.bombadu.bitcoinexchange.network


import com.google.gson.annotations.SerializedName

class NomicsTickerData : ArrayList<NomicsTickerData.NomicsTickerDataItem>(){
    data class NomicsTickerDataItem(
        @SerializedName("circulating_supply")
        var circulatingSupply: String,
        var currency: String,
        @SerializedName("1d")
        var d: D,
        @SerializedName("first_candle")
        var firstCandle: String,
        @SerializedName("first_order_book")
        var firstOrderBook: String,
        @SerializedName("first_trade")
        var firstTrade: String,
        var high: String,
        @SerializedName("high_timestamp")
        var highTimestamp: String,
        var id: String,
        @SerializedName("logo_url")
        var logoUrl: String,
        @SerializedName("market_cap")
        var marketCap: String,
        @SerializedName("market_cap_dominance")
        var marketCapDominance: String,
        @SerializedName("max_supply")
        var maxSupply: String,
        var name: String,
        @SerializedName("num_exchanges")
        var numExchanges: String,
        @SerializedName("num_pairs")
        var numPairs: String,
        @SerializedName("num_pairs_unmapped")
        var numPairsUnmapped: String,
        var price: String,
        @SerializedName("price_date")
        var priceDate: String,
        @SerializedName("price_timestamp")
        var priceTimestamp: String,
        var rank: String,
        @SerializedName("rank_delta")
        var rankDelta: String,
        var status: String,
        var symbol: String
    ) {
        data class D(
            @SerializedName("market_cap_change")
            var marketCapChange: String,
            @SerializedName("market_cap_change_pct")
            var marketCapChangePct: String,
            @SerializedName("price_change")
            var priceChange: String,
            @SerializedName("price_change_pct")
            var priceChangePct: String,
            var volume: String,
            @SerializedName("volume_change")
            var volumeChange: String,
            @SerializedName("volume_change_pct")
            var volumeChangePct: String
        )
    

    }
}