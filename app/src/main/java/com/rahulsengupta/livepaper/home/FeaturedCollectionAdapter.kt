package com.rahulsengupta.livepaper.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.livepaper.databinding.ItemFeaturedCollectionHomeBinding
import com.rahulsengupta.livepaper.home.model.FeaturedCollectionItem

class FeaturedCollectionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var featuredCollections: List<FeaturedCollectionItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemFeaturedCollectionHomeBinding.inflate(LayoutInflater.from(parent.context))
        return FeaturedItemViewHolder(binding)
    }

    override fun getItemCount() = featuredCollections.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FeaturedItemViewHolder).bind(featuredCollections[position])
    }

    fun updateList(list: List<FeaturedCollectionItem>) {
        featuredCollections = list
        notifyDataSetChanged()
    }

    inner class FeaturedItemViewHolder(val binding: ItemFeaturedCollectionHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FeaturedCollectionItem) {
            binding.run {
                this.item = item
                executePendingBindings()
            }
        }
    }
}