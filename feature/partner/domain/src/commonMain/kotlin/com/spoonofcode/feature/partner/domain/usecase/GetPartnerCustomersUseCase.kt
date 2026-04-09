package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.Customer
import com.spoonofcode.feature.partner.domain.model.ProductSeries
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

class GetPartnerCustomersUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(): Result<List<Customer>> = partnerRepository.getPartnerCustomers()
}