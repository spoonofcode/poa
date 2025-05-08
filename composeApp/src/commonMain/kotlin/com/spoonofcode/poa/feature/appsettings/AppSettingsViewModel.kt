package com.spoonofcode.poa.feature.appsettings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.HasSpecialSettingsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class AppSettingsViewModel(
    private val hasSpecialSettingsUseCase: HasSpecialSettingsUseCase,
) : BaseViewModel<AppSettingsViewState>(AppSettingsViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val hasSpecialSettings = hasSpecialSettingsUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }
}