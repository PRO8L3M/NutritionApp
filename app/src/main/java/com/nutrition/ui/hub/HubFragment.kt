package com.nutrition.ui.hub

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nutrition.R
import kotlinx.android.synthetic.main.fragment_hub.*

class HubFragment: Fragment(R.layout.fragment_hub) {
    @SuppressLint("OnClickListenerDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exoplayerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_hubFragment_to_startFragment)
        }

        viewpagerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_hubFragment_to_viewPagerFragment)
        }

        wheelPicker.setOnClickListener {
            findNavController().navigate(R.id.action_hubFragment_to_wheelPickerFragment)
        }

        customComponents.setOnClickListener {
            findNavController().navigate(R.id.action_hubFragment_to_customUiComponentsFragment)
        }

        statusBarPic.setOnClickListener {
            findNavController().navigate(R.id.action_hubFragment_to_pictureStatusBarFragment)
        }

        swipeRemoving.setOnClickListener {
            findNavController().navigate(R.id.action_hubFragment_to_swipeRemovingViewHolderItemFragment)
        }
    }
}