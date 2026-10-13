package com.spoonofcode.feature.partner.data.test

import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.model.ProductSeries

object PartnerMockData {
    val PARTNER_1 = Partner(
        id = "550e8400-e29b-41d4-a716-446655440001",
        partnerCategory = "1",
        name = "Beauty Saute",
        email = "beauty.saute@gmail.com",
        description = "Beauty Sautè to więcej niż odzież – to filozofia.",
        imageLink = "https://beautysaute.pl/environment/cache/images/0_0_storefrontImages_11a16d30-d85a-422f-917d-195c30274147.png",
        websiteLink = "https://beautysaute.pl/",
        instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
        youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
    )
    val PARTNER_2 = Partner(
        id = "550e8400-e29b-41d4-a716-446655440002",
        partnerCategory = "2",
        name = "Proof of Wear",
        email = "proof.of.wear@gmail.com",
        description = "Proof of Wear – sprawdz to.",
        imageLink = "https://proof-of-wear.com/images/logo.jpg",
        websiteLink = "https://proof-of-wear.com/",
        instagramLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
        facebookLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
        youtubeLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
        xLink = "https://x.com/ProofOfWearPOW?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor",
    )
    val PARTNERS = listOf(PARTNER_1, PARTNER_2)

    val PRODUCT_SERIES_1 = ProductSeries(
        askContactMessage = "Ask contact message 1",
        banner = "https://example.com/banner1.jpg",
        bannerIsOn = true,
        blockchain = "Ethereum",
        contactAddress = "0x123",
        description = "Description 1",
        enabled = true,
        facebook = "https://facebook.com",
        fullName = "Full Name 1",
        instagram = "https://instagram.com",
        message = "Message 1",
        name = "Series 1",
        owner = "Owner 1",
        ownerEmail = "owner1@example.com",
        partnerLink = "https://partner1.com",
        partnerLinkText = "Partner Link Text 1",
        tiktok = "https://tiktok.com",
        x = "https://x.com",
        youtube = "https://youtube.com",
    )

    val PRODUCT_SERIES_2 = ProductSeries(
        askContactMessage = "Ask contact message 2",
        banner = "https://example.com/banner2.jpg",
        bannerIsOn = true,
        blockchain = "Ethereum",
        contactAddress = "0x456",
        description = "Description 2",
        enabled = true,
        facebook = "https://facebook.com",
        fullName = "Full Name 2",
        instagram = "https://instagram.com",
        message = "Message 2",
        name = "Series 2",
        owner = "Owner 2",
        ownerEmail = "owner2@example.com",
        partnerLink = "https://partner2.com",
        partnerLinkText = "Partner Link Text 2",
        tiktok = "https://tiktok.com",
        x = "https://x.com",
        youtube = "https://youtube.com",
    )

    val PRODUCT_SERIES_LIST = listOf(PRODUCT_SERIES_1, PRODUCT_SERIES_2)
}
