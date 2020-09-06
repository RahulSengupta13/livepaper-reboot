package com.rahulsengupta.livepaper.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rahulsengupta.livepaper.databinding.ItemPopularPhotoHomeBinding
import com.rahulsengupta.livepaper.home.model.PopularPhotoItem

class PopularPhotosAdapter : PagingDataAdapter<PopularPhotoItem, ViewHolder>(COMPARATOR) {

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

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PopularPhotoItem>() {
            override fun areItemsTheSame(oldItem: PopularPhotoItem, newItem: PopularPhotoItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PopularPhotoItem, newItem: PopularPhotoItem) =
                oldItem == newItem
        }
    }
}

