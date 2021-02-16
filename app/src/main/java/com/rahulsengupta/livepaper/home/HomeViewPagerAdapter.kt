package com.rahulsengupta.livepaper.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rahulsengupta.livepaper.home.collections.CollectionsFragment
import com.rahulsengupta.livepaper.home.photos.PhotosFragment

class HomeViewPagerAdapter constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PhotosFragment.newInstance()
            else -> CollectionsFragment.newInstance()
        }
    }

    companion object {
        private const val FRAGMENT_COUNT = 2
    }
}