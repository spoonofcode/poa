package com.spoonofcode.feature.partner.presentation.notification.details

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerNotificationDetailsViewAction : BaseViewAction {
    data object EditNotification : PartnerNotificationDetailsViewAction
    data object DeleteNotification : PartnerNotificationDetailsViewAction
    data object CancelDeleteNotification : PartnerNotificationDetailsViewAction
    data object ConfirmDeleteNotification : PartnerNotificationDetailsViewAction
}