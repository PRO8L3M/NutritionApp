package com.nutrition.ui.swipeRecyclerView

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nutrition.R
import kotlinx.android.synthetic.main.view_pager_fragment.*

class SwipeRemovingViewHolderItemFragment : Fragment(R.layout.fragment_swipe_removing) {

    private val adapter = SwipeRemovingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.apply {
            
        }
    }

}