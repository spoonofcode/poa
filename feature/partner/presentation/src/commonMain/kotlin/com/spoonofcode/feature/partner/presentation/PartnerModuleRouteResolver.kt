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
        ).Content()
    }

    entry<PartnerModuleRoute.PartnerDetails> {
        PartnerDetailsScreen(
            partnerId = it.partnerId,
            viewModel = koinViewModel()
        ).Content()
    }

    entry<PartnerModuleRoute.PartnerEdit> {
        PartnerEditScreen(
            viewModel = koinViewModel()
        ).Content()
    }

    entry<PartnerModuleRoute.PartnerOverview> {
        PartnerOverviewScreen(
            viewModel = koinViewModel()
        ).Content()
    }

    entry<PartnerModuleRoute.PartnerPanel> {
        PartnerPanelScreen(
            viewModel = koinViewModel()
        ).Content()
    }

    entry<PartnerModuleRoute.PartnerNotifications> {
        PartnerNotificationsScreen(
            viewModel = koinViewModel()
        ).Content()
    }

    entry<PartnerModuleRoute.PartnerNotificationDetails> {
        PartnerNotificationDetailsScreen(
            notificationId = it.notificationId,
            viewModel = koinViewModel()
        ).Content()
    }

    entry<PartnerModuleRoute.PartnerNotificationEdit> {
        PartnerNotificationEditScreen(
            notificationId = it.notificationId,
            selectedNames = it.selectedNames,
            selectedTopics = it.selectedTopics,
            viewModel = koinViewModel()
        ).Content()
    }

    entry<PartnerModuleRoute.PartnerNotificationType> {
        PartnerNotificationTypeScreen(
            viewModel = koinViewModel()
        ).Content()
    }


}