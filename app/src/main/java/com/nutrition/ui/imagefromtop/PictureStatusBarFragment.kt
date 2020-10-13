package com.nutrition.ui.imagefromtop

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import coil.load
import com.nutrition.R
import kotlinx.android.synthetic.main.fragment_status_bar_picture.*

class PictureStatusBarFragment : Fragment(R.layout.fragment_status_bar_picture) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSolution()
        //secondSolution()

        toolbarImage.load("https://i.pinimg.com/originals/00/01/c0/0001c005b9aece8969ed51a20af4c2cd.jpg")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        firstSolutionOnClean(requireContext())
        //secondSolutionOnClean()
    }


    private fun firstSolution() {
        activity?.window?.let {
            with(it) {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                statusBarColor = Color.TRANSPARENT
            }
        }
    }

    private fun firstSolutionOnClean(context: Context) {
        activity?.window?.let {
            with(it) {
                statusBarColor = context.getColor(R.color.colorPrimaryDark)
            }
        }

    }

    private fun secondSolution() {
        (activity as Activity).window.apply {
            setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    private fun secondSolutionOnClean() {
        (activity as Activity).window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}
