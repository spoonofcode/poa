package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.NFTInfoRepository
import com.spoonofcode.poa.core.model.NFTInfo

// TODO Fix this use case
class GetNFTInfoByIdUseCase(
    private val nftInfoRepository: NFTInfoRepository,
) {
    suspend operator fun invoke(
        nftInfoId: String
    ): NFTInfo = NFTInfo(
        tagId = "Ashlen",
        publicId = "Meliza",
        series = "Janene",
        readCtr = "Omer",
        owner = "Deron",
        ownerEmail = "Genna",
        contractAdress = "Janessa",
        tokenId = "Johnthan",
        secret = "Andrae",
        nftImage = "Tamisha",
        nftAnimation = "Raine",
        nftName = "Alba",
        blockchain = "Raeanna",
        link = "Nhung",
        linkText = "Anand"

    )
//        nftInfoRepository.read(id = nftInfoId)
}