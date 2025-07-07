package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.NFTInfoRepository
import com.spoonofcode.poa.core.model.NFTInfo
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// TODO Fix this use case
class GetNFTInfoByIdUseCase(
    private val nftInfoRepository: NFTInfoRepository,
) {
    suspend operator fun invoke(
        nftInfoId: String
    ): NFTInfo = NFTInfo(
        tagId = "04A971E2E51090",
        publicId = "4",
        series = "Gen 1",
        timestamp = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        readCtr = "Ames",
        owner = "Ames",
        ownerEmail = "adam@testmail.com",
        contractAddress = "0x867008929c6500645128695b633ff1b68ed232ae",
        tokenId = "1",
        secret = "Blain",
        nftImage = "https://i.seadn.io/s/raw/files/ec3d7f1edc5e5b3699b8d5c1d1e0623a.png",
        nftAnimation = "https://raw.seadn.io/files/2a839046d7178aea08c51551c82d9f5e.mp4",
        nftName = "Proof of Wear gen1",
        blockchain = "matic",
        link = "https://www.moneymakesmoney.info/nbx2003/",
        linkText = "Link do strony klienta 1"
    )
//        nftInfoRepository.read(id = nftInfoId)
}