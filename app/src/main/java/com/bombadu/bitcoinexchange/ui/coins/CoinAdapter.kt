package com.bombadu.bitcoinexchange.ui.coins

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.bombadu.bitcoinexchange.R
import com.bombadu.bitcoinexchange.local.LocalTickerData
import java.math.BigDecimal
import java.math.RoundingMode


class CoinAdapter : ListAdapter<LocalTickerData, CoinAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.coin_info_item, parent, false)
        )


    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.coin_name_text_view)
        private val logoImageView = itemView.findViewById<ImageView>(R.id.coin_logo_image_view)
        private val priceTextView = itemView.findViewById<TextView>(R.id.coin_price_text_view)
        private val changeTextView = itemView.findViewById<TextView>(R.id.coin_change_text_view)
        private val symbolTextView = itemView.findViewById<TextView>(R.id.coin_symbol_text_view)



        private val imageLoader = ImageLoader.Builder(itemView.context)
            .componentRegistry {
                add(SvgDecoder(itemView.context))

            }
            .build()

        @SuppressLint("SetTextI18n")
        fun bind(item: LocalTickerData) = with(itemView) {

            val pctChange = (item.changePct).toDouble() * 100
            val rawPrice = item.price
            val change = BigDecimal(pctChange).setScale(2, RoundingMode.HALF_EVEN).toString()
            val price = BigDecimal(rawPrice).setScale(4, RoundingMode.HALF_EVEN).toString()

            nameTextView.text = item.name
            priceTextView.text = "$$price"
            logoImageView.load(item.logoUrl, imageLoader)
            if (change.contains("-")) {
                changeTextView.text = "$change%"
                changeTextView.setTextColor(Color.RED)
            } else {
                changeTextView.text = "$change%"
                changeTextView.setTextColor(Color.BLACK)
            }


            symbolTextView.text = item.symbol

               setOnClickListener {
                   val intent = Intent(context, CoinDetailActivity::class.java)
                   intent.putExtra(SELECTED_COIN, item)
                   context.startActivity(intent)
            }
        }


    }

    companion object {
        val TAG = CoinAdapter::class.java.simpleName
        const val SELECTED_COIN ="selected_coin"
    }
}



class DiffCallback : DiffUtil.ItemCallback<LocalTickerData>() {

    override fun areItemsTheSame(oldItem: LocalTickerData, newItem: LocalTickerData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LocalTickerData, newItem: LocalTickerData): Boolean {
        return oldItem.id == newItem.id

    }
}