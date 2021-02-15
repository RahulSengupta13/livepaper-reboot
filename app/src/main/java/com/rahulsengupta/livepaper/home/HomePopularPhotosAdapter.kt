package com.rahulsengupta.livepaper.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rahulsengupta.core.ui.loadImageWithPalette
import com.rahulsengupta.livepaper.databinding.ItemPopularPhotoHomeBinding
import com.rahulsengupta.livepaper.home.model.PhotoItem

class HomePopularPhotosAdapter : PagingDataAdapter<PhotoItem, ViewHolder>(COMPARATOR) {

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
            with(ConstraintSet()) {
                clone(binding.container)
                setDimensionRatio(binding.itemPopularPhotoImageview.id, "${item.width}:${item.height}")
                applyTo(binding.container)
            }

            with(binding) {
                this.item = item
                executePendingBindings()
                itemPopularPhotoImageview.loadImageWithPalette(item.imageUrl) { gradientDrawable, textColor ->
                    binding.banner.setBackgroundDrawable(gradientDrawable)
                    binding.authorImage.borderColor = textColor
                }
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PhotoItem>() {
            override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem) = oldItem == newItem
            override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem) = oldItem == newItem
        }
    }
}

