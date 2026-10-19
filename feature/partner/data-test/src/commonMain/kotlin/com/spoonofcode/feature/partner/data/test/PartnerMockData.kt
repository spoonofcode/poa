package com.spoonofcode.feature.partner.data.test

import com.spoonofcode.feature.partner.domain.model.Customer
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.model.ProductSeries

object PartnerMockData {
    val PARTNER_1 = Partner(
        id = "550e8400-e29b-41d4-a716-446655440001",
        name = "Beauty Saute",
        email = "beauty.saute@gmail.com",
        description = "Beauty Sautè to więcej niż odzież – to filozofia.",
        imageLink = "https://beautysaute.pl/environment/cache/images/0_0_storefrontImages_11a16d30-d85a-422f-917d-195c30274147.png",
        websiteLink = "https://beautysaute.pl/",
        instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
        youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        partnerCategory = "Beauty",
    )
    val PARTNER_2 = Partner(
        id = "550e8400-e29b-41d4-a716-446655440002",
        name = "Proof of Wear",
        email = "proof.of.wear@gmail.com",
        description = "Proof of Wear – sprawdz to.",
        imageLink = "https://proof-of-wear.com/images/logo.jpg",
        websiteLink = "https://proof-of-wear.com/",
        instagramLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
        facebookLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
        youtubeLink = "https://www.instagram.com/proof_of_wear/?igsh=MXMwODNiZW1qNTRlMA%3D%3D#",
        xLink = "https://x.com/ProofOfWearPOW?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor",
        partnerCategory = "2",
    )
    val PARTNERS = listOf(PARTNER_1, PARTNER_2)

    val PRODUCT_SERIES_1 = ProductSeries(
        name = "Series 1",
        fullName = "Full Name 1",
        enabled = true,
        description = "Description 1",
        ownerEmail = "owner1@example.com",
        owner = "Owner 1",
        redirectLink = "https://example.com/redirect",
        askContactMessage = "Ask contact message 1",
        blockchain = "Ethereum",
        bannerIsOn = true,
        banner = "https://example.com/banner1.jpg",
        message = "Message 1",
        partnerLink = "https://partner1.com",
        partnerLinkText = "Partner Link Text 1",
        x = "https://x.com",
        instagram = "https://instagram.com",
        youtube = "https://youtube.com",
        facebook = "https://facebook.com",
        tiktok = "https://tiktok.com",
    )

    val PRODUCT_SERIES_2 = ProductSeries(
        name = "Series 2",
        fullName = "Full Name 2",
        enabled = true,
        description = "Description 2",
        ownerEmail = "owner2@example.com",
        owner = "Owner 2",
        redirectLink = "https://example.com/redirect",
        askContactMessage = "Ask contact message 2",
        blockchain = "Ethereum",
        bannerIsOn = true,
        banner = "https://example.com/banner2.jpg",
        message = "Message 2",
        partnerLink = "https://partner2.com",
        partnerLinkText = "Partner Link Text 2",
        x = "https://x.com",
        instagram = "https://instagram.com",
        youtube = "https://youtube.com",
        facebook = "https://facebook.com",
        tiktok = "https://tiktok.com",
    )

    val PRODUCT_SERIES_LIST = listOf(PRODUCT_SERIES_1, PRODUCT_SERIES_2)

    val CUSTOMER_1 = Customer(
        id = "c1",
        email = "customer1@example.com",
        name = "Customer 1",
    )
    val CUSTOMER_2 = Customer(
        id = "c2",
        email = "customer2@example.com",
        name = "Customer 2",
    )
    val CUSTOMERS = listOf(CUSTOMER_1, CUSTOMER_2)
}
