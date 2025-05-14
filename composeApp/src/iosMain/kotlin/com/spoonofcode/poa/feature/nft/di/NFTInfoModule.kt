package com.spoonofcode.poa.feature.nft.di

import com.spoonofcode.poa.feature.nft.details.NFTInfoDetailsViewModel
import com.spoonofcode.poa.feature.nft.edit.NFTInfoEditViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val nftInfoModule = module {
    singleOf(::NFTInfoDetailsViewModel)
    singleOf(::NFTInfoEditViewModel)
}