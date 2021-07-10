package com.bombadu.bitcoinexchange.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "exchange_table", indices = [Index(value = ["name", "imageUrl"], unique = true)])
@Parcelize
class ExchangeEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "established") var established: String,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "hasTradingIncentive") var hasTradingIncentive: Boolean,
    @ColumnInfo(name = "trustScore") var trustScore: Int,
    @ColumnInfo(name = "trustRank") var trustRank: Int,
    @ColumnInfo(name = "tradeVolume") var tradeVolume: Double
) : Parcelable {

    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
