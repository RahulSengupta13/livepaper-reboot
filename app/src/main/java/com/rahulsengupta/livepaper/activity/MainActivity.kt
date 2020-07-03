package com.rahulsengupta.livepaper.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rahulsengupta.livepaper.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Apply default transition on app launch
        motion_layout.setTransition(motion_layout.currentState, R.id.home_expand)
        motion_layout.transitionToEnd()

        setClickListener()
    }

    private fun setClickListener() {
        iv_home.setOnClickListener {
            setTransition(motion_layout.currentState, R.id.home_expand)
        }

        iv_search.setOnClickListener {
            setTransition(motion_layout.currentState, R.id.search_expand)
        }

        iv_like.setOnClickListener {
            setTransition(motion_layout.currentState, R.id.like_expand)
        }

        iv_profile.setOnClickListener {
            setTransition(motion_layout.currentState, R.id.profile_expand)
        }
    }

    private fun setTransition(startState: Int, endState: Int) {
        motion_layout.setTransition(startState, endState)
        motion_layout.setTransitionDuration(250)
        motion_layout.transitionToEnd()
    }
}