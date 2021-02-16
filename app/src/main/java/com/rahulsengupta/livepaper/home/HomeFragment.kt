package com.rahulsengupta.livepaper.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.rahulsengupta.livepaper.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeViewpager.adapter = HomeViewPagerAdapter(this)
        TabLayoutMediator(binding.homeTabLayout, binding.homeViewpager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Photos"
                }
                else -> {
                    tab.text = "Collections"
                }
            }
            binding.homeViewpager.setCurrentItem(position, true)
        }.attach()

        binding.homeViewpager.setCurrentItem(0, true)
    }
}