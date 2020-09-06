package com.rahulsengupta.livepaper.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.livepaper.databinding.ItemFeaturedCollectionHomeBinding
import com.rahulsengupta.livepaper.home.model.FeaturedCollectionItem

class FeaturedCollectionAdapter : ListAdapter<FeaturedCollectionItem, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemFeaturedCollectionHomeBinding.inflate(LayoutInflater.from(parent.context))
        return FeaturedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FeaturedItemViewHolder).bind(getItem(position))
    }

    /*ViewHolder*/
    class FeaturedItemViewHolder(val binding: ItemFeaturedCollectionHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FeaturedCollectionItem) {
            binding.run {
                this.item = item
                executePendingBindings()
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<FeaturedCollectionItem>() {
        override fun areItemsTheSame(
            oldItem: FeaturedCollectionItem,
            newItem: FeaturedCollectionItem
        ) = oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: FeaturedCollectionItem,
            newItem: FeaturedCollectionItem
        ) = oldItem == newItem
    }
}