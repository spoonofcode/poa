package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.data.repository.PartnerFilterRepository
import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.model.Partner
import kotlin.time.ExperimentalTime

class GetPartnersFilteredByCategoryUseCase(
    private val partnerRepository: PartnerRepository,
    private val partnerFilterRepository: PartnerFilterRepository,
) {
    suspend operator fun invoke(): List<Partner> {
        // TODO Change with data from API
//        val partners = partnerRepository.readAll()
        val partners = getMockedPartners()

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

@OptIn(ExperimentalTime::class)
private fun getMockedPartners(): List<Partner> {
    return listOf(
        Partner(
            id = 1,
            name = "Beauty Saute",
            description = "Beauty Sautè to więcej niż odzież – to filozofia.",
            websiteLink = "https://beautysaute.pl/",
            instagramLink = "https://beautysaute.pl/",
            facebookLink = "https://beautysaute.pl/",
            youtubeLink = "https://beautysaute.pl/",
            xLink = "https://beautysaute.pl/",
        ),
        Partner(
            id = 2,
            name = "Proof of Wear",
            description = "Proof of Wear – sprawdz to.",
            websiteLink = "https://proof-of-wear.com/",
            instagramLink = "https://proof-of-wear.com/",
            facebookLink = "https://proof-of-wear.com/",
            youtubeLink = "https://proof-of-wear.com/",
            xLink = "https://proof-of-wear.com/",
        ),
    )
}

