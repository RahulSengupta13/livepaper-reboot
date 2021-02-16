package com.rahulsengupta.livepaper.home.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rahulsengupta.livepaper.databinding.FragmentCollectionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionsFragment: Fragment() {

    companion object {
        fun newInstance() = CollectionsFragment()
    }

    private lateinit var binding: FragmentCollectionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}