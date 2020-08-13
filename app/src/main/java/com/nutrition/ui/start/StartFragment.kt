package com.nutrition.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nutrition.R
import com.nutrition.common.BaseFragment
import com.nutrition.ext.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : BaseFragment() {

    private val viewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textFromRepo = viewModel.getText()
        toast(textFromRepo)
    }
}
