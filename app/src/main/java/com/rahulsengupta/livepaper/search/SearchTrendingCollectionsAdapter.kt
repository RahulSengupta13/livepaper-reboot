package com.rahulsengupta.livepaper.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.livepaper.databinding.ItemTrendingCollectionSearchBinding
import com.rahulsengupta.livepaper.databinding.ItemTrendingCollectionSearchEmptyBinding
import com.rahulsengupta.livepaper.search.model.TrendingCollectionItem
import com.rahulsengupta.livepaper.search.model.TrendingCollectionItemViewType

class SearchTrendingCollectionsAdapter : ListAdapter<TrendingCollectionItem, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TrendingCollectionItemViewType.REGULAR.viewType -> {
                val binding = ItemTrendingCollectionSearchBinding.inflate(inflater, parent, false)
                TrendingCollectionViewHolder(binding)
            }
            else -> {
                val binding = ItemTrendingCollectionSearchEmptyBinding.inflate(inflater, parent, false)
                EmptyTrendingCollectionViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = getItem(position)) {
            is TrendingCollectionItem.Regular -> {
                (holder as TrendingCollectionViewHolder).bind(item)
            }
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).viewType

    //ViewHolders
    class TrendingCollectionViewHolder(val binding: ItemTrendingCollectionSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingCollectionItem.Regular) {
            binding.run {
                this.item = item
                executePendingBindings()
            }
        }
    }

    class EmptyTrendingCollectionViewHolder(val binding: ItemTrendingCollectionSearchEmptyBinding) : RecyclerView.ViewHolder(binding.root)

    object DiffCallback : DiffUtil.ItemCallback<TrendingCollectionItem>() {
        override fun areItemsTheSame(
            oldItem: TrendingCollectionItem,
            newItem: TrendingCollectionItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TrendingCollectionItem,
            newItem: TrendingCollectionItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}