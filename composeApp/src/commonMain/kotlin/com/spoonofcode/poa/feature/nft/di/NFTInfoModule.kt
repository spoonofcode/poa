package com.spoonofcode.poa.feature.nft.di

import com.spoonofcode.poa.feature.nft.details.NFTInfoDetailsViewModel
import com.spoonofcode.poa.feature.nft.edit.NFTInfoEditViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val nftInfoModule = module {
    viewModelOf(::NFTInfoDetailsViewModel)
    viewModelOf(::NFTInfoEditViewModel)
}