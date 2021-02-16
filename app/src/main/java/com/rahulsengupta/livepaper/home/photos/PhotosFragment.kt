package com.rahulsengupta.livepaper.home.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rahulsengupta.core.ui.FabScrollVisibilityHelper
import com.rahulsengupta.core.ui.FastScroller
import com.rahulsengupta.livepaper.databinding.FragmentPhotosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
@AndroidEntryPoint
class PhotosFragment: Fragment() {

    companion object {
        fun newInstance() = PhotosFragment()
    }

    private lateinit var binding: FragmentPhotosBinding
    private lateinit var recyclerViewlayoutManager: StaggeredGridLayoutManager
    private var fetchPopularPhotosJob: Job? = null

    private val popularPhotosAdapter = PhotosAdapter()
    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPhotosBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewlayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        }

        with(binding.featurePopularPhotoRecyclerview) {
            layoutManager = recyclerViewlayoutManager
            isNestedScrollingEnabled = true
            adapter = popularPhotosAdapter
            addOnScrollListener(FabScrollVisibilityHelper(binding.homePopularPhotosFab))
        }

        viewModel.viewEffect.observe(viewLifecycleOwner) {
            when(it) {
                is ViewEffect.ScrollToTop -> scrollToTop()
                is ViewEffect.RefreshLatestPhotos -> refreshLatestPhotos()
            }
        }

        lifecycleScope.launch {
            popularPhotosAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect {
                    binding.swipeRefresh.isRefreshing = false
                }
        }

        with(binding.swipeRefresh) {
            isRefreshing = true
        }

        refreshLatestPhotos()
    }

    private fun scrollToTop() {
        val smoothScroller = FastScroller(requireContext()).apply { targetPosition = 0 }
        recyclerViewlayoutManager.startSmoothScroll(smoothScroller)
    }

    private fun refreshLatestPhotos() {
        fetchPopularPhotosJob?.cancel()
        fetchPopularPhotosJob = lifecycleScope.launch {
            viewModel.fetchPopularPhotos().collectLatest {
                popularPhotosAdapter.submitData(it)
            }
        }
    }
}