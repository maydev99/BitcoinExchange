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

        val priceRaw = coinDetail.price
        val price = BigDecimal(priceRaw).setScale(4, RoundingMode.HALF_EVEN).toString()
        binding.detailPriceTextView.text = "$${price}"

        binding.detailChangeTextView.text = "$${coinDetail.price} / ${coinDetail.changePct}%"





        //Toast.makeText(this, "${coinDetail.name}", Toast.LENGTH_SHORT).show()
    }
}