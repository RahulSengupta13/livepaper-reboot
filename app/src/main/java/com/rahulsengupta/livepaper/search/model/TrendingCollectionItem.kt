package com.rahulsengupta.livepaper.search.model

import com.rahulsengupta.livepaper.search.model.TrendingCollectionItemViewType.EMPTY
import com.rahulsengupta.livepaper.search.model.TrendingCollectionItemViewType.REGULAR

sealed class TrendingCollectionItem(val viewType: Int) {

    data class Regular(
        val title: String,
        val imageUrl: String
    ) : TrendingCollectionItem(REGULAR.viewType)

    object Empty : TrendingCollectionItem(EMPTY.viewType)
}

enum class TrendingCollectionItemViewType(val viewType: Int) {
    REGULAR(viewType = 0),
    EMPTY(viewType = 1)
}