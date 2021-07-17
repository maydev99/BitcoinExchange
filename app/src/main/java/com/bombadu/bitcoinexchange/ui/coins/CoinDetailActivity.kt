package com.bombadu.bitcoinexchange.ui.coins

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.bombadu.bitcoinexchange.databinding.ActivityCoinDetailBinding
import com.bombadu.bitcoinexchange.local.LocalTickerData
import com.bombadu.bitcoinexchange.ui.coins.CoinAdapter.Companion.SELECTED_COIN
import java.math.BigDecimal
import java.math.RoundingMode

class CoinDetailActivity : AppCompatActivity() {

    lateinit var coinDetail: LocalTickerData
    private lateinit var binding: ActivityCoinDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageLoader = ImageLoader.Builder(this)
            .componentRegistry {
                add(SvgDecoder(this@CoinDetailActivity))

            }
            .build()

        coinDetail = intent.getParcelableExtra(SELECTED_COIN)!!

        binding.detailNameTextView.text = coinDetail.name
        binding.detailImageView.load(coinDetail.logoUrl, imageLoader)


        //Format Price
        val priceRaw = coinDetail.price
        val price = decimalRoundToString(priceRaw, 4)


        //Format Change/ Change%
        val change = decimalRoundToString(coinDetail.change.toString(), 4)
        val changePctRaw = coinDetail.changePct.toDouble() * 100
        val changePct = decimalRoundToString(changePctRaw.toString(), 2)

        //Format High
        val high = decimalRoundToString(coinDetail.high, 2)
        val highTimeStamp = coinDetail.high_timeStamp

        //Volume Pct
        val volPctRaw = coinDetail.volume_change_pct.toDouble() * 100
        val valPct = decimalRoundToString(volPctRaw.toString(), 2)


        //Set Views
        binding.detailPriceTextView.text = "$${price}"
        binding.detailChangeTextView.text = "$${change} / ${changePct}%"
        binding.detailSymbolTextView.text = coinDetail.symbol
        binding.detailCircSupplyTextView.text = coinDetail.circulating_supply
        binding.detailMarketCapTextView.text = coinDetail.marketCap
        binding.detailRankTextView.text = coinDetail.rank
        binding.detailHighTextView.text = "$$high"
        binding.detailHighTimeStampTextView.text = "($highTimeStamp)"
        binding.detailVolumeTextView.text = coinDetail.volume
        binding.detailVolumePctChangeTextView.text = "$valPct%"









        //Toast.makeText(this, "${coinDetail.name}", Toast.LENGTH_SHORT).show()
    }

    private fun decimalRoundToString(num: String, dec: Int) : String {
        return BigDecimal(num).setScale(dec, RoundingMode.HALF_EVEN).toString()

    }
}