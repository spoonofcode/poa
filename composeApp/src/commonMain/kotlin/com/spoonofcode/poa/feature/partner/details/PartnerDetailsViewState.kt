package com.spoonofcode.poa.feature.partner.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class PartnerDetailsViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val partner: Partner = Partner(
        id = "550e8400-e29b-41d4-a716-446655440001",
        name = "Beauty Saute",
        description = "Beauty Sautè to więcej niż odzież – to filozofia.",
        imageLink = "https://beautysaute.pl/environment/cache/images/0_0_storefrontImages_11a16d30-d85a-422f-917d-195c30274147.png",
        email = "beauty.saute@gmail.com",
        websiteLink = "https://beautysaute.pl/",
        instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
        youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
        userId = "550e8400-e29b-41d4-a716-446655440001",
    ),
) : BaseViewState(screenState)