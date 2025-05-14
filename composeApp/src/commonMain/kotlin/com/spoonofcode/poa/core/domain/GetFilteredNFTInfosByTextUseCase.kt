package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.model.NFTInfo

class GetFilteredNFTInfosByTextUseCase {
    operator fun invoke(
        searchText: String,
        nftInfos: List<NFTInfo>,
    ): List<NFTInfo> = nftInfos.filter { nftInfo ->
        nftInfo.nftName.contains(searchText, ignoreCase = true)
    }
}

