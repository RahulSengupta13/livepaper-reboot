package com.rahulsengupta.livepaper.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_NONE
import com.rahulsengupta.livepaper.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var fetchLatestPhotosJob: Job? = null

    private val latestPhotosAdapter = HomeLatestPhotosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.featureCollectionHomeRecyclerview) {
            adapter = FeaturedCollectionAdapter()
        }

        with(binding.featurePopularPhotoRecyclerview) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                gapStrategy = GAP_HANDLING_NONE
            }
            isNestedScrollingEnabled = true
            adapter = latestPhotosAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        refreshLatestPhotos()
    }

    private fun refreshLatestPhotos() {
        fetchLatestPhotosJob?.cancel()
        fetchLatestPhotosJob = lifecycleScope.launch {
            viewModel.fetchLatestPhotos().collectLatest {
                latestPhotosAdapter.submitData(it)
            }
        }
    }
}