package com.spoonofcode.poa.core.domain.old

import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterRepository
import com.spoonofcode.poa.core.data.repository.old.NFTInfoRepository
import com.spoonofcode.poa.core.model.old.NFTInfo
import com.spoonofcode.poa.feature.search.filter.FilterViewState
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

class GetFilteredNFTInfosUseCase(
    private val nftInfoRepository: NFTInfoRepository,
    private val NFTInfoFilterRepository: NFTInfoFilterRepository,
) {
    suspend operator fun invoke(): List<NFTInfo> {
        // TODO Change with data from API
//        val nftInfos = nftInfoRepository.readAll()
        val nftInfos = getMockedNFTInfos()

        val selectedFilter = NFTInfoFilterRepository.getSelectedFilters()
        return nftInfos.filter { nftInfo ->
            val matchesSeriesFilter = selectedFilter.selectedSerieId == null ||
                    selectedFilter.selectedSerieId == FilterViewState.ALL_OPTION_NAME ||
                    nftInfo.series == selectedFilter.selectedSerieId
            val matchesBlockchainFilter = selectedFilter.selectedBlockchainId == null ||
                    selectedFilter.selectedBlockchainId == FilterViewState.ALL_OPTION_NAME ||
                    nftInfo.blockchain == selectedFilter.selectedBlockchainId
            matchesSeriesFilter && matchesBlockchainFilter
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun getMockedNFTInfos(): List<NFTInfo> {
        return listOf(
            NFTInfo(
                tagId = "tagID-1",
                publicId = "publicID-1",
                series = "Gen 1",
                timestamp = Clock.System.now().toLocalDateTime(TimeZone.UTC),
                readCtr = "Adam",
                owner = "Adam",
                ownerEmail = "adam@testmail.com",
                contractAddress = "0x867008929c6500645128695b633ff1b68ed232ae",
                tokenId = "1",
                secret = "Blain",
                nftImage = "https://i.seadn.io/s/raw/files/ec3d7f1edc5e5b3699b8d5c1d1e0623a.png",
                nftAnimation = "https://raw.seadn.io/files/2a839046d7178aea08c51551c82d9f5e.mp4",
                nftName = "Proof of Wear gen1",
                blockchain = "matic",
                link = "https://proof-of-wear.com",
                linkText = "Link do strony klienta 1"
            ),
            NFTInfo(
                tagId = "tagID-2",
                publicId = "publicID-2",
                series = "Gen 2",
                timestamp = Clock.System.now().toLocalDateTime(TimeZone.UTC),
                readCtr = "Bartek",
                owner = "Bartek",
                ownerEmail = "bartek@testmail.com",
                contractAddress = "0x867008929c6500645128695b633ff1b68ed232ae",
                tokenId = "2",
                secret = "Blain",
                nftImage = "https://i.seadn.io/s/raw/files/ec3d7f1edc5e5b3699b8d5c1d1e0623a.png",
                nftAnimation = "https://raw.seadn.io/files/2a839046d7178aea08c51551c82d9f5e.mp4",
                nftName = "Proof of Wear gen2",
                blockchain = "matic",
                link = "https://proof-of-wear.com",
                linkText = "Link do strony klienta 2"
            ),
            NFTInfo(
                tagId = "tagID-3",
                publicId = "publicID-3",
                series = "Gen 3",
                timestamp = Clock.System.now().toLocalDateTime(TimeZone.UTC),
                readCtr = "Michal",
                owner = "Michal",
                ownerEmail = "michal@testmail.com",
                contractAddress = "0x867008929c6500645128695b633ff1b68ed232ae",
                tokenId = "3",
                secret = "Blain",
                nftImage = "https://i.seadn.io/s/raw/files/ec3d7f1edc5e5b3699b8d5c1d1e0623a.png",
                nftAnimation = "https://raw.seadn.io/files/2a839046d7178aea08c51551c82d9f5e.mp4",
                nftName = "Proof of Wear gen3",
                blockchain = "matic",
                link = "https://proof-of-wear.com",
                linkText = "Link do strony klienta 3"
            ),
        )
    }
}

