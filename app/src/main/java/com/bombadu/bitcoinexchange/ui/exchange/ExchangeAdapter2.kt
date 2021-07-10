package com.bombadu.bitcoinexchange.ui.exchange

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.bitcoinexchange.R
import com.bombadu.bitcoinexchange.local.ExchangeEntity
import com.squareup.picasso.Picasso


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
        val scoreTextView = itemView.findViewById<TextView>(R.id.scoreTextView)
        val rankTextView = itemView.findViewById<TextView>(R.id.rankTextView)

        @SuppressLint("SetTextI18n")
        fun bind(item: ExchangeEntity) = with(itemView) {
            Picasso.get().load(item.imageUrl).into(imageView)
            nameTextView.text = item.name
            estTextView.text = "est. ${item.established}"
            volTextView.text = item.tradeVolume.toString()
            scoreTextView.text = item.trustScore.toString()
            rankTextView.text = item.trustRank.toString()

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