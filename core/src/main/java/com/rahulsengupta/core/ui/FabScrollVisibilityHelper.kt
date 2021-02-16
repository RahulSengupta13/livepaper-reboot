package com.rahulsengupta.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabScrollVisibilityHelper(private val fab: FloatingActionButton) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0 && fab.visibility == View.VISIBLE) {
            fab.hide()
        } else if (dy < 0 && fab.visibility != View.VISIBLE) {
            fab.show()
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.childCount > 0 && recyclerView.getChildAt(0).top == 0) {
            fab.hide()
        }
        super.onScrollStateChanged(recyclerView, newState)
    }
}