package com.spoonofcode.core.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.PermissionState
import com.mohamedrejeb.calf.permissions.PermissionStatus

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun RequestPermission(
    notificationPermissionState: PermissionState,
    onGrantedAction: () -> Unit,
    onShowRationalAction: () -> Unit,
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                when (val status = notificationPermissionState.status) {
                    PermissionStatus.Granted -> onGrantedAction()
                    is PermissionStatus.Denied -> {
                        if (status.shouldShowRationale) {
                            onShowRationalAction()
                        } else {
                            notificationPermissionState.launchPermissionRequest()
                        }
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}