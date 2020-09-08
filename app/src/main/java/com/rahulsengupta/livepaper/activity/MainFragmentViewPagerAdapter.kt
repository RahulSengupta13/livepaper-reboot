package com.rahulsengupta.livepaper.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rahulsengupta.livepaper.favorites.FavoritesFragment
import com.rahulsengupta.livepaper.home.HomeFragment
import com.rahulsengupta.livepaper.search.SearchFragment
import com.rahulsengupta.livepaper.settings.SettingsFragment

class MainFragmentViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = FRAGMENT__COUNT

    @OptIn(ExperimentalPagingApi::class)
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> SearchFragment.newInstance()
            2 -> FavoritesFragment.newInstance()
            3 -> SettingsFragment.newInstance()
            else -> HomeFragment.newInstance()
        }
    }

    companion object {
        private const val FRAGMENT__COUNT = 4
    }
}