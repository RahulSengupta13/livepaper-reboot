package com.rahulsengupta.livepaper.home.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rahulsengupta.core.ui.loadImageWithPalette
import com.rahulsengupta.livepaper.databinding.ItemCollectionHomeBinding
import com.rahulsengupta.livepaper.home.collections.model.CollectionItem

class CollectionsAdapter : PagingDataAdapter<CollectionItem, ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as CollectionItemViewHolder).bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectionHomeBinding.inflate(inflater, parent, false)
        return CollectionItemViewHolder(binding)
    }

    //ViewHolders
    class CollectionItemViewHolder(val binding: ItemCollectionHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CollectionItem) {
            with(ConstraintSet()) {
                clone(binding.container)
                setDimensionRatio(binding.itemPopularPhotoImageview.id, "${item.coverPhotoWidth}:${item.coverPhotoHeight}")
                applyTo(binding.container)
            }

            with(binding) {
                this.item = item
                executePendingBindings()
                itemPopularPhotoImageview.loadImageWithPalette(item.coverPhoto) { gradientDrawable, textColor ->
                    binding.banner.setBackgroundDrawable(gradientDrawable)
                    binding.authorImage.borderColor = textColor
                }
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CollectionItem>() {
            override fun areItemsTheSame(oldItem: CollectionItem, newItem: CollectionItem) = oldItem == newItem
            override fun areContentsTheSame(oldItem: CollectionItem, newItem: CollectionItem) = oldItem == newItem
        }
    }
}

