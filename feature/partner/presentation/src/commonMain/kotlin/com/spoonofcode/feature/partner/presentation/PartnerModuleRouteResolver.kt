package com.spoonofcode.feature.partner.presentation


import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.presentation.customer.overview.CustomerOverviewScreen
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsScreen
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditScreen
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationsScreen
import com.spoonofcode.feature.partner.presentation.notification.details.PartnerNotificationDetailsScreen
import com.spoonofcode.feature.partner.presentation.notification.edit.PartnerNotificationEditScreen
import com.spoonofcode.feature.partner.presentation.notification.type.PartnerNotificationTypeScreen
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewScreen
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelScreen
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.partnerModuleRouteResolver() {
    entry<PartnerModuleRoute.CustomerOverview> {
        CustomerOverviewScreen(
            viewModel = koinViewModel()
        )
    }

    entry<PartnerModuleRoute.PartnerDetails> {
        PartnerDetailsScreen(
            partnerId = it.partnerId,
            viewModel = koinViewModel()
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
            notificationId = it.notificationId,
            viewModel = koinViewModel()
        )
    }

    entry<PartnerModuleRoute.PartnerNotificationEdit> {
        PartnerNotificationEditScreen(
            notificationId = it.notificationId,
            selectedNames = it.selectedNames,
            selectedTopics = it.selectedTopics,
            viewModel = koinViewModel()
        )
    }

    entry<PartnerModuleRoute.PartnerNotificationType> {
        PartnerNotificationTypeScreen(
            viewModel = koinViewModel()
        )
    }


}