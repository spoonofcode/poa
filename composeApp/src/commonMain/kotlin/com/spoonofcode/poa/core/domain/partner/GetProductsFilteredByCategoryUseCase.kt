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
            email = "beauty.saute@gmail.com",
            description = "Beauty Sautè to więcej niż odzież – to filozofia.",
            websiteLink = "https://beautysaute.pl/",
            instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
            facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
            youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
            xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        ),
        Partner(
            id = 2,
            name = "Proof of Wear",
            email = "proof.of.wear@gmail.com",
            description = "Proof of Wear – sprawdz to.",
            websiteLink = "https://proof-of-wear.com/",
            instagramLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
            facebookLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
            youtubeLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
            xLink = "https://x.com/ProofOfWearPOW?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor",
        ),
    )
}

