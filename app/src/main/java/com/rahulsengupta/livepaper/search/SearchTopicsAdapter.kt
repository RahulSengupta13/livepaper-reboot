package com.rahulsengupta.livepaper.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rahulsengupta.livepaper.databinding.ItemSearchMostViewedBinding
import com.rahulsengupta.livepaper.search.model.TopicsItem

class SearchTopicsAdapter : PagingDataAdapter<TopicsItem, ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as SearchTopicsViewHolder).bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchMostViewedBinding.inflate(inflater, parent, false)
        return SearchTopicsViewHolder(binding)
    }

    //ViewHolder
    class SearchTopicsViewHolder(val binding: ItemSearchMostViewedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TopicsItem) {
            binding.run {
                this.item = item
                executePendingBindings()
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<TopicsItem>() {
        override fun areItemsTheSame(oldItem: TopicsItem, newItem: TopicsItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TopicsItem, newItem: TopicsItem) =
            oldItem == newItem
    }
}

