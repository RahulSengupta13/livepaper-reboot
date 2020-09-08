package com.rahulsengupta.livepaper.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.livepaper.search.model.TrendingCollectionItem

@BindingAdapter("trendingCollections")
fun trendingCollections(recycler: RecyclerView, list: List<TrendingCollectionItem>?) {
    list ?: return
    (recycler.adapter as SearchTrendingCollectionsAdapter).submitList(list)
}