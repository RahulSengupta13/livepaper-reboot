package com.rahulsengupta.livepaper.activity

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.rahulsengupta.livepaper.R
import com.rahulsengupta.livepaper.activity.ViewEffect.NavigateToHome
import com.rahulsengupta.livepaper.activity.ViewEffect.NavigateToSearch
import com.rahulsengupta.livepaper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.homeFragment,
            R.id.searchFragment
        )
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
            syncBottomBar()
        }

        viewModel.viewEffect.observe(this, Observer {
            when (it) {
                is NavigateToHome -> navigateToHome()
                is NavigateToSearch -> navigateToSearch()
                else -> Unit
            }
        })
    }

    override fun onBackPressed() {
       if(currentNavId == R.id.homeFragment) finish() else navController.navigateUp()
    }

    private fun navigateToSearch() {
        try {
            val backStackEntry = navController.getBackStackEntry(R.id.searchFragment)
            navController.popBackStack(backStackEntry.destination.id, false)
        } catch (e: Exception) {
            navController.navigate(R.id.action_global_searchFragment)
        }
    }

    private fun navigateToHome() {
        try {
            val backStackEntry = navController.getBackStackEntry(R.id.homeFragment)
            navController.popBackStack(backStackEntry.destination.id, false)
        } catch (e: Exception) {
            navController.navigate(R.id.action_global_homeFragment)
        }
    }

    private fun syncBottomBar() {
        binding.bottomBarMotionLayout.run {
            val endState = when (currentNavId) {
                R.id.searchFragment -> R.id.search_expand
                else -> R.id.home_expand
            }
            setTransition(currentState, endState)
            setTransitionDuration(250)
            transitionToEnd()
        }
    }
}