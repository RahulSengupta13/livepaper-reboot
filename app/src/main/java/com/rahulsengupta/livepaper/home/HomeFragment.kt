package com.rahulsengupta.livepaper.home

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
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import com.rahulsengupta.core.ui.FastScroller
import com.rahulsengupta.livepaper.databinding.FragmentHomeBinding
import com.rahulsengupta.livepaper.home.ViewEffect.RefreshLatestPhotos
import com.rahulsengupta.livepaper.home.ViewEffect.ScrollToTop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerViewlayoutManager: StaggeredGridLayoutManager
    private var fetchPopularPhotosJob: Job? = null

    private val viewModel: HomeViewModel by viewModels()
    private val popularPhotosAdapter = HomePopularPhotosAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewlayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
            gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        }

        with(binding.featurePopularPhotoRecyclerview) {
            layoutManager = recyclerViewlayoutManager
            isNestedScrollingEnabled = true
            adapter = popularPhotosAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && binding.homePopularPhotosFab.visibility == View.VISIBLE) {
                        binding.homePopularPhotosFab.hide()
                    } else if (dy < 0 && binding.homePopularPhotosFab.visibility != View.VISIBLE) {
                        binding.homePopularPhotosFab.show()
                    }
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if(newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.childCount > 0 && recyclerView.getChildAt(0).top == 0) {
                        binding.homePopularPhotosFab.hide()
                    }
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
        }

        viewModel.viewEffect.observe(viewLifecycleOwner) {
            when(it) {
                is ScrollToTop -> scrollToTop()
                is RefreshLatestPhotos -> refreshLatestPhotos()
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