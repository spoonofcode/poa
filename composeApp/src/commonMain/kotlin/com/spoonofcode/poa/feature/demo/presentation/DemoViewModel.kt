package com.spoonofcode.poa.feature.demo.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class DemoViewModel() : BaseViewModel<DemoViewState, DemoViewAction>(DemoViewState()) {

    override fun onAction(action: DemoViewAction) {
        when (action) {
            DemoViewAction.InitView -> initView()
        }
    }

    private fun initView() {
        println("Loading data...")
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            delay(3000L)
            updateState { copy(isLoading = false) }
        }
    }
}