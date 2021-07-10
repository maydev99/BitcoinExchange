package com.bombadu.bitcoinexchange.network


import com.google.gson.annotations.SerializedName

class ExchangeData : ArrayList<ExchangeData.ExchangeDataItem>(){

    data class ExchangeDataItem(
        var country: String,
        var description: String,
        @SerializedName("has_trading_incentive")
        var hasTradingIncentive: Boolean,
        var id: String,
        var image: String,
        var name: String,
        @SerializedName("trade_volume_24h_btc")
        var tradeVolume24hBtc: Double,
        @SerializedName("trade_volume_24h_btc_normalized")
        var tradeVolume24hBtcNormalized: Double,
        @SerializedName("trust_score")
        var trustScore: Int,
        @SerializedName("trust_score_rank")
        var trustScoreRank: Int,
        var url: String,
        @SerializedName("year_established")
        var yearEstablished: Int
    )
}