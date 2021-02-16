package com.rahulsengupta.livepaper.home.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rahulsengupta.core.ui.FabScrollVisibilityHelper
import com.rahulsengupta.core.ui.FastScroller
import com.rahulsengupta.livepaper.databinding.FragmentCollectionsBinding
import com.rahulsengupta.livepaper.home.collections.ViewEffect.RefreshCollections
import com.rahulsengupta.livepaper.home.collections.ViewEffect.ScrollToTop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CollectionsFragment: Fragment() {

    companion object {
        fun newInstance() = CollectionsFragment()
    }

    private lateinit var recyclerViewlayoutManager: StaggeredGridLayoutManager
    private lateinit var binding: FragmentCollectionsBinding
    private var collectionsJob: Job? = null

    private val viewModel: CollectionsViewModel by viewModels()
    private val collectionsAdapter = CollectionsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewlayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL).apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        }

        with(binding.collectionsRecyclerview) {
            layoutManager = recyclerViewlayoutManager
            isNestedScrollingEnabled = true
            adapter = collectionsAdapter
            addOnScrollListener(FabScrollVisibilityHelper(binding.collectionsFab))
        }

        viewModel.viewEffect.observe(viewLifecycleOwner) {
            when(it) {
                is RefreshCollections -> refreshCollections()
                is ScrollToTop -> scrollToTop()
            }
        }

        lifecycleScope.launch {
            collectionsAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect {
                    binding.swipeRefresh.isRefreshing = false
                }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshCollections()
    }

    private fun refreshCollections() {
        collectionsJob?.cancel()
        collectionsJob = lifecycleScope.launch {
            viewModel.getCollections().collectLatest {
                collectionsAdapter.submitData(it)
            }
        }
    }

    private fun scrollToTop() {
        val smoothScroller = FastScroller(requireContext()).apply { targetPosition = 0 }
        recyclerViewlayoutManager.startSmoothScroll(smoothScroller)
    }
}