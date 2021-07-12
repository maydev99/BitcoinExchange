package com.bombadu.bitcoinexchange.network


class CoinListData : ArrayList<CoinListData.CoinListDataItem>(){
    data class CoinListDataItem(
        var id: String,
        var name: String,
        var symbol: String
    )
}