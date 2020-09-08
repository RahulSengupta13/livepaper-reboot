package com.rahulsengupta.livepaper.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rahulsengupta.livepaper.databinding.FragmentSplashBinding
import com.rahulsengupta.livepaper.splash.Command.NavigateToMainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.command.observe(viewLifecycleOwner, Observer {
            when (it) {
                NavigateToMainFragment -> navigateToHome()
            }
        })
    }

    private fun navigateToHome() {
        val action = SplashFragmentDirections.actionSplashToHome()
        findNavController().navigate(action)
    }
}