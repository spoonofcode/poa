package com.spoonofcode.feature.partner.presentation


import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewScreen
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsScreen
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditScreen
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationsScreen
import com.spoonofcode.feature.partner.presentation.notification.details.PartnerNotificationDetailsScreen
import com.spoonofcode.feature.partner.presentation.notification.edit.PartnerNotificationEditArgs
import com.spoonofcode.feature.partner.presentation.notification.edit.PartnerNotificationEditScreen
import com.spoonofcode.feature.partner.presentation.notification.type.PartnerNotificationTypeScreen
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewScreen
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.partnerModuleRouteResolver() {
    entry<PartnerModuleRoute.CustomerOverview> {
        PartnerCustomerOverviewScreen(
            viewModel = koinViewModel()
        )
    }

    entry<PartnerModuleRoute.PartnerDetails> {
        PartnerDetailsScreen(
            viewModel = koinViewModel(parameters = { parametersOf(it.partnerId) })
        )
    }

    entry<PartnerModuleRoute.PartnerEdit> {
        PartnerEditScreen(
            viewModel = koinViewModel()
        )
    }

    entry<PartnerModuleRoute.PartnerOverview> {
        PartnerOverviewScreen(
            viewModel = koinViewModel()
        )
    }

    entry<PartnerModuleRoute.PartnerPanel> {
        PartnerPanelScreen(
            viewModel = koinViewModel()
        )
    }

    entry<PartnerModuleRoute.PartnerNotifications> {
        PartnerNotificationsScreen(
            viewModel = koinViewModel()
        )
    }

    entry<PartnerModuleRoute.PartnerNotificationDetails> {
        PartnerNotificationDetailsScreen(
            viewModel = koinViewModel(parameters = { parametersOf(it.notificationId) })
        )
    }

    entry<PartnerModuleRoute.PartnerNotificationEdit> {
        PartnerNotificationEditScreen(
            viewModel = koinViewModel(
                parameters = {
                    parametersOf(
                        PartnerNotificationEditArgs(
                            notificationId = it.notificationId,
                            selectedTopics = it.selectedTopics,
                            selectedNames = it.selectedNames,
                        )
                    )
                }
            )
        )
    }

    entry<PartnerModuleRoute.PartnerNotificationType> {
        PartnerNotificationTypeScreen(
            viewModel = koinViewModel()
        )
    }


}