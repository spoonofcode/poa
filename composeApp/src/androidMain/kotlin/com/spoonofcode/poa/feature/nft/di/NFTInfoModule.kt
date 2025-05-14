package com.spoonofcode.poa.feature.nft.di

import com.spoonofcode.poa.feature.nft.details.NFTInfoDetailsViewModel
import com.spoonofcode.poa.feature.nft.edit.NFTInfoEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val nftInfoModule = module {
    viewModelOf(::NFTInfoDetailsViewModel)
    viewModelOf(::NFTInfoEditViewModel)
}