package com.rahulsengupta.livepaper.home

import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.livepaper.databinding.ItemPopularPhotoHomeBinding
import com.rahulsengupta.livepaper.home.model.PhotoItem

class PopularItemViewHolder(val binding: ItemPopularPhotoHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PhotoItem) {
        binding.run {
            this.item = item
            executePendingBindings()
        }
    }
}