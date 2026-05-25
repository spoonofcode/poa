package com.spoonofcode.feature.partner.presentation.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditScreen
import com.spoonofcode.feature.profile.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel(
    private val getProfileUseCase: GetProfileUseCase
) :
    BaseViewModel<PartnerPanelViewState, PartnerPanelViewAction>(
        initialViewState = PartnerPanelViewState(),
        initialScreenState = ScreenState.LOADING,
    ) {

    override fun onAction(action: PartnerPanelViewAction) {
        when (action) {
            PartnerPanelViewAction.InitView -> initView()
            PartnerPanelViewAction.NavigateToNotificationType -> navigateToNotificationType()
            PartnerPanelViewAction.NavigateToPartnerEdit -> navigateToPartnerEdit()
            PartnerPanelViewAction.NavigateToReports -> navigateToReports()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getProfileUseCase()
                .onSuccess {
                    showContentView {
                        copy(partnerId = it.id)
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    fun navigateToPartnerEdit() {
        val partnerId = viewState.value.data.partnerId ?: return
        viewModelScope.launch {
            viewModelNavigator.push(PartnerEditScreen(partnerId = partnerId))
        }
    }

    fun navigateToNotificationType() {
        val partnerId = viewState.value.data.partnerId ?: return
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    NotificationModuleRoute.NotificationType(
                        partnerId = partnerId
                    )
                )
            )
        }
    }

    fun navigateToReports() {
        // TODO Add reports screen
    }
}
