package com.bombadu.bitcoinexchange.ui.coins

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.bombadu.bitcoinexchange.R
import com.bombadu.bitcoinexchange.local.LocalTickerData
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.roundToInt


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
        private val logoImageView= itemView.findViewById<ImageView>(R.id.coin_logo_image_view)
        private val priceTextView = itemView.findViewById<TextView>(R.id.coin_price_text_view)

        private val imageLoader = ImageLoader.Builder(itemView.context)
            .componentRegistry {
                add(SvgDecoder(itemView.context))

            }
            .build()

        @SuppressLint("SetTextI18n")
        fun bind(item: LocalTickerData) = with(itemView) {

            nameTextView.text = item.name
            priceTextView.text = "$${item.price}"
            logoImageView.load(item.logoUrl, imageLoader)



            /*Picasso.get().load(logoUrl)
                .placeholder(R.drawable.placeholder_drawable)
                .error(R.drawable.placeholder_drawable)
                .into(logoImageView)*/






            /*   setOnClickListener {
                val intent = Intent(context, PrePlayerDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putString("video_url", item.videoUlr)
                bundle.putString("title", item.title)
                bundle.putBoolean("has_video", item.hasVideo)
                bundle.putString("key", item.key)
                bundle.putString("rank", item.rank)
                bundle.putString("synopsis", item.synopsis)
                bundle.putString("release_date", item.releaseDate)
                bundle.putString("backdrop_url", item.backDropUrl)
                intent.putExtras(bundle)
                context.startActivity(intent)

            }*/
        }

        fun ImageView.loadUrl(url: String) {

            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(context)) }
                .build()

            val request = ImageRequest.Builder(this.context)
                .crossfade(true)
                .crossfade(500)
                .placeholder(R.drawable.placeholder_drawable)
                .error(R.drawable.placeholder_drawable)
                .data(url)
                .target(this)
                .build()

            imageLoader.enqueue(request)
        }

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