package com.rahulsengupta.livepaper.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.core.ui.SliderTransformer
import com.rahulsengupta.livepaper.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

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

        val adapter = SearchPopularPhotosAdapter()
        binding.popularPhotosViewPager.apply {
            this.adapter = adapter
            (getChildAt(0) as? RecyclerView)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            offscreenPageLimit = 4
            setPageTransformer(SliderTransformer(4))

//            val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.view_pager_page_margin_small)
//            setPageTransformer(
//                CompositePageTransformer().apply {
//                    addTransformer(MarginPageTransformer(pageMarginPx))
//                    addTransformer(ScaleTransformer())
//                }
//            )
        }


        binding.viewpagerPagerIndicator.attachToViewPager2(binding.popularPhotosViewPager)

        lifecycleScope.launch {
            viewModel.popularPhotoFlow.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}