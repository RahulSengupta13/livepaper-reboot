package com.rahulsengupta.livepaper.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rahulsengupta.livepaper.databinding.ItemPopularPhotoHomeBinding
import com.rahulsengupta.livepaper.home.model.PhotoItem

class HomeLatestPhotosAdapter : PagingDataAdapter<PhotoItem, ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as PopularItemViewHolder).bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPopularPhotoHomeBinding.inflate(inflater, parent, false)
        return PopularItemViewHolder(binding)
    }

    //ViewHolders
    class PopularItemViewHolder(val binding: ItemPopularPhotoHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhotoItem) {
            binding.run {
                this.item = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PhotoItem>() {
            override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem) =
                oldItem == newItem
        }
    }
}

