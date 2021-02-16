package com.rahulsengupta.livepaper.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.core.ui.SliderTransformer
import com.rahulsengupta.livepaper.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    lateinit var binding: FragmentSearchBinding

    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * Latest Photos
        * */
        val latestPhotoAdapter = SearchLatestPhotosAdapter()
        with(binding.popularPhotosViewPager) {
            this.adapter = latestPhotoAdapter
            (getChildAt(0) as? RecyclerView)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            offscreenPageLimit = 4
            setPageTransformer(SliderTransformer(4))
            binding.viewpagerPagerIndicator.attachToViewPager2(this)
        }
        lifecycleScope.launch {
            viewModel.latestPhotosFlow.collectLatest {
                latestPhotoAdapter.submitData(it)
            }
        }

        /*
        * Topics(Most Viewed)
        * */
        val topicsAdapter = SearchTopicsAdapter()
        with(binding.mostViewedRecyclerview) {
            adapter = topicsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }

        lifecycleScope.launch {
            viewModel.topicsFlow.collectLatest {
                topicsAdapter.submitData(it)
            }
        }
    }
}