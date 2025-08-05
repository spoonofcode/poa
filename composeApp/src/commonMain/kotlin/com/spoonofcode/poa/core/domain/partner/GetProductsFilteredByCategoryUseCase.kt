package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.data.repository.PartnerFilterRepository
import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.model.Partner

class GetPartnersFilteredByCategoryUseCase(
    private val partnerRepository: PartnerRepository,
    private val partnerFilterRepository: PartnerFilterRepository,
) {
    suspend operator fun invoke(): List<Partner> {
        // TODO Change with data from API
        val partners = partnerRepository.readAll()
//        val partners = getMockedPartners()

        return partners

//        val selectedFilter = partnerFilterRepository.getSelectedFilters()
//        return partners.filter { partner ->
//            val matchesNameFilter = selectedFilter.selectedName == null ||
//                    selectedFilter.selectedName == PartnerFilterViewState.ALL_OPTION_NAME ||
//                    partner.name == selectedFilter.selectedName
//            val matchesCollectionNameFilter = selectedFilter.selectedCollectionName == null ||
//                    selectedFilter.selectedCollectionName == PartnerFilterViewState.ALL_OPTION_NAME ||
//                    partner.collectionName == selectedFilter.selectedCollectionName
//            matchesNameFilter && matchesCollectionNameFilter

    }
}
