package com.spoonofcode.poa.core.domain.old

import com.spoonofcode.poa.core.model.old.NFTInfo

class GetFilteredNFTInfosByTextUseCase {
    operator fun invoke(
        searchText: String,
        nftInfos: List<NFTInfo>,
    ): List<NFTInfo> = nftInfos.filter { nftInfo ->
        nftInfo.nftName.contains(searchText, ignoreCase = true)
    }
}

