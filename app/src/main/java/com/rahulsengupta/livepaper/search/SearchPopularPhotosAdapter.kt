package com.rahulsengupta.livepaper.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rahulsengupta.livepaper.databinding.ItemLatestPhotoSearchBinding
import com.rahulsengupta.livepaper.home.model.PhotoItem

class SearchPopularPhotosAdapter : PagingDataAdapter<PhotoItem, ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as SearchPopularPhotoViewHolder).bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLatestPhotoSearchBinding.inflate(inflater, parent, false)
        return SearchPopularPhotoViewHolder(binding)
    }

    //ViewHolder
    class SearchPopularPhotoViewHolder(val binding: ItemLatestPhotoSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

