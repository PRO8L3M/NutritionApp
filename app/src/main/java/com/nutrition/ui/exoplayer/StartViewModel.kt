package com.nutrition.ui.exoplayer

import androidx.lifecycle.ViewModel
import com.nutrition.data.repository.Repository

class StartViewModel(private val repository: Repository) : ViewModel() {

    fun getText() = repository.text
}