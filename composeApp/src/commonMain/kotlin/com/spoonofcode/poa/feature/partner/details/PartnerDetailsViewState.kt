package com.spoonofcode.poa.feature.partner.details

import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class PartnerDetailsViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val partner: Partner = Partner(
        id = 1,
        name = "Beauty Saute",
        description = "Beauty Sautè to więcej niż odzież – to filozofia.",
        email = "beauty.saute@gmail.com",
        websiteLink = "https://beautysaute.pl/",
        instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
        youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
    ),
) : BaseViewState()