package com.spoonofcode.poa.feature.notification.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.notification.GetNotificationByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationDetailsViewModel(
    private val getNotificationByIdUseCase: GetNotificationByIdUseCase,
) : BaseViewModel<NotificationDetailsViewState>(NotificationDetailsViewState()) {

    fun initView(notificationId: Int) {
        viewModelScope.launch {
            showLoadingView()
            try {
                val notification = getNotificationByIdUseCase(notificationId = notificationId)
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        notification = notification
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }


    fun deleteNotification() {
//        viewModelScope.launch {
//            showLoadingView()
//            try {
//                val currentState = currentState()
//                deleteProductUseCase(
//                    nftId = currentState.nft!!.id,
//                )
//                viewModelNavigator.pop()
//            } catch (ce: CancellationException) {
//                throw ce
//            } catch (e: Exception) {
//                showErrorSnackbar(e)
//            }
//        }
    }


    private fun showLoadingView() {
        updateState {
            copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
            )
        }
    }
}