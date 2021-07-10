package com.bombadu.bitcoinexchange.ui.exchange

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.bitcoinexchange.R
import com.bombadu.bitcoinexchange.databinding.ExchangeItemBinding
import com.bombadu.bitcoinexchange.local.ExchangeEntity

class ExchangeAdapter(val onClickListener: OnClickListener) :
    ListAdapter<ExchangeEntity, ExchangeAdapter.ExchangeEntityViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<ExchangeEntity>() {
        override fun areItemsTheSame(oldItem: ExchangeEntity, newItem: ExchangeEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ExchangeEntity, newItem: ExchangeEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExchangeEntityViewHolder {
        return ExchangeEntityViewHolder(ExchangeItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ExchangeEntityViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    class ExchangeEntityViewHolder(private var binding: ExchangeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup) : ExchangeEntityViewHolder {
                val binding: ExchangeItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.exchange_item,
                    parent,
                    false
                )

                return ExchangeEntityViewHolder(binding)
            }
        }

        fun bind(item: ExchangeEntity) {
            binding.exchangeEntity = item
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }





    class OnClickListener(val clickListener: (exchangeEntity: ExchangeEntity) -> Unit) {
        fun onClick(exchangeEntity: ExchangeEntity) = clickListener(exchangeEntity)
    }
}