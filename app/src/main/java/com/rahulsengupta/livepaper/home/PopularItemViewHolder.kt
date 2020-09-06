package com.rahulsengupta.livepaper.home

import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.livepaper.databinding.ItemPopularPhotoHomeBinding
import com.rahulsengupta.livepaper.home.model.PopularPhotoItem

class PopularItemViewHolder(val binding: ItemPopularPhotoHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PopularPhotoItem) {
        binding.run {
            this.item = item
            executePendingBindings()
        }
    }
}