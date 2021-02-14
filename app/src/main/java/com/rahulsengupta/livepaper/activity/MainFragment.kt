package com.rahulsengupta.livepaper.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rahulsengupta.livepaper.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.mainFragmentViewPager) {
            isUserInputEnabled = false
            adapter = MainFragmentViewPagerAdapter(requireActivity()).apply {
                offscreenPageLimit = 4
            }
        }

        viewModel.command.observe(viewLifecycleOwner, {
            when (it) {
                is Command.NavigateToIndex -> {
                    binding.mainFragmentViewPager.setCurrentItem(it.index, true)
                }
                else -> Unit
            }
        })
    }
}