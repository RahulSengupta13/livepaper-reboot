package com.rahulsengupta.livepaper.activity

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.rahulsengupta.livepaper.R
import com.rahulsengupta.livepaper.activity.Command.NavigateToIndex
import com.rahulsengupta.livepaper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(R.id.mainFragment)
    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var currentNavId = R.id.splashFragment

    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        navController = findNavController(R.id.nav_fragment)
        appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)

        binding.activityMainContainer.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentNavId = destination.id
            val isTopLevelDestination = TOP_LEVEL_DESTINATIONS.contains(destination.id)
            binding.bottomBarLayout.visibility = if (isTopLevelDestination) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        viewModel.command.observe(this, {
            when (it) {
                is NavigateToIndex -> syncBottomBar(it.index)
            }
        })
    }

    private fun syncBottomBar(index: Int) {
        binding.bottomBarMotionLayout.run {
            val endState = when (index) {
                1 -> R.id.search_expand
                2 -> R.id.like_expand
                3 -> R.id.profile_expand
                else -> R.id.home_expand
            }
            setTransition(currentState, endState)
            setTransitionDuration(250)
            transitionToEnd()
        }
    }
}