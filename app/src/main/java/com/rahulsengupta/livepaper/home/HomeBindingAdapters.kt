package com.rahulsengupta.livepaper.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.livepaper.home.model.FeaturedCollectionItem

@BindingAdapter("app:featuredCollections")
fun featuredCollections(recycler: RecyclerView, list: List<FeaturedCollectionItem>?) {
    val featuredCollections = list ?: return
    (recycler.adapter as FeaturedCollectionAdapter).submitList(featuredCollections)
}