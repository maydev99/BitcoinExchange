package com.bombadu.bitcoinexchange.ui.exchange

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bombadu.bitcoinexchange.R
import com.bombadu.bitcoinexchange.local.ExchangeEntity
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt


class ExchangeAdapter2 :
    ListAdapter<ExchangeEntity, ExchangeAdapter2.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.exchange_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.logoImageView)
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val estTextView = itemView.findViewById<TextView>(R.id.estTextView)
        val volTextView = itemView.findViewById<TextView>(R.id.volumeTextView)
        val rankTextView = itemView.findViewById<TextView>(R.id.rankTextView)

        @SuppressLint("SetTextI18n")
        fun bind(item: ExchangeEntity) = with(itemView) {
            //Picasso.get().load(item.imageUrl).into(imageView)
            imageView.load(item.imageUrl)
            nameTextView.text = item.name
            estTextView.text = "Est. ${item.established}"
            volTextView.text = " Volume: ${item.tradeVolume.roundToInt()}"
            rankTextView.text = item.trustRank.toString()

               setOnClickListener {
                   val intent = Intent(Intent.ACTION_VIEW)
                   intent.data = Uri.parse(item.url)
                   context.startActivity(intent)

                /*val intent = Intent(context, PrePlayerDetailActivity::class.java)
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
                context.startActivity(intent)*/

            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ExchangeEntity>() {

    override fun areItemsTheSame(oldItem: ExchangeEntity, newItem: ExchangeEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ExchangeEntity, newItem: ExchangeEntity): Boolean {
        return oldItem.id == newItem.id
    }
}