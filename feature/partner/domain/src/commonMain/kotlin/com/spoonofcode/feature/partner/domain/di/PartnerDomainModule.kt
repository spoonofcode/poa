package com.spoonofcode.feature.partner.domain.di

import com.spoonofcode.feature.partner.domain.usecase.CreatePartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.DeletePartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetNewPartnersUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerByIdUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerCategoriesUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerCustomersUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationTopicUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationsUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerProductSeriesUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersFilteredByPartnerCategoryUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersFilteredByTextUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersUseCase
import com.spoonofcode.feature.partner.domain.usecase.PartnerSendNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.UpdatePartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.UpdatePartnerUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val partnerDomainModule =
    module {
        factoryOf(::DeletePartnerNotificationUseCase)
        factoryOf(::GetPartnerByIdUseCase)
        factoryOf(::GetPartnerCategoriesUseCase)
        factoryOf(::GetPartnersFilteredByPartnerCategoryUseCase)
        factoryOf(::GetPartnersFilteredByTextUseCase)
        factoryOf(::GetPartnersUseCase)
        factoryOf(::GetNewPartnersUseCase)
        factoryOf(::GetPartnerProductSeriesUseCase)
        factoryOf(::GetPartnerCustomersUseCase)
        factoryOf(::GetPartnerUseCase)
        factoryOf(::GetPartnerNotificationsUseCase)
        factoryOf(::GetPartnerNotificationUseCase)
        factoryOf(::CreatePartnerNotificationUseCase)
        factoryOf(::PartnerSendNotificationUseCase)
        factoryOf(::GetPartnerNotificationTopicUseCase)
        factoryOf(::UpdatePartnerUseCase)
        factoryOf(::UpdatePartnerNotificationUseCase)
    }
