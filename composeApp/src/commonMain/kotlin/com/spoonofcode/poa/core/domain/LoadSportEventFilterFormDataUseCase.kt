package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.BlockchainRepository
import com.spoonofcode.poa.core.data.repository.NFTSerieRepository
import com.spoonofcode.poa.core.model.Blockchain
import com.spoonofcode.poa.core.model.NFTSerie
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

data class SportEventFilterFormData(
    val nftSeries: List<NFTSerie>,
    val blockchains: List<Blockchain>,
)

class LoadSportEventFilterFormDataUseCase(
    private val nftSerieRepository: NFTSerieRepository,
    private val blockchainRepository: BlockchainRepository,
) {
    suspend operator fun invoke(): SportEventFilterFormData {
        return coroutineScope {
            val nftSeriesAsync = async { nftSerieRepository.readAll() }
            val blockchainsAsync = async { blockchainRepository.readAll() }

            val nftSeries = nftSeriesAsync.await()
            val blockchains = blockchainsAsync.await()

            SportEventFilterFormData(
                nftSeries = nftSeries,
                blockchains = blockchains,
            )
        }
    }
}