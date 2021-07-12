package com.bombadu.bitcoinexchange.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.bitcoinexchange.R
import com.bombadu.bitcoinexchange.local.ExchangeEntity
import com.bombadu.bitcoinexchange.ui.exchange.ExchangeAdapter2
import com.squareup.picasso.Picasso

@BindingAdapter("exchangeImage")
fun bindExchangeImageToItemImageView(imageView: ImageView, url: String) {
    Picasso.get().load(url)
        .placeholder(R.drawable.placeholder_drawable)
        .error(R.drawable.placeholder_drawable)
        .into(imageView)
}

@BindingAdapter("setExchangeRecyclerView")
fun bindExchangeRecyclerView(recyclerView: RecyclerView, exData: List<ExchangeEntity>?) {
    val adapter = recyclerView.adapter as ExchangeAdapter2
    adapter.submitList(exData)
}