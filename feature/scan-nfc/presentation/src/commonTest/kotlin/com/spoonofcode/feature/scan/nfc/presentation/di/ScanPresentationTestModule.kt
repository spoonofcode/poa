package com.spoonofcode.feature.scan.nfc.presentation.di

import com.spoonofcode.core.data.getPlatform
import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.feature.product.data.test.di.productDataTestModule
import com.spoonofcode.feature.product.domain.di.productDomainModule
import com.spoonofcode.feature.scan.nfc.domain.di.scanDomainModule
import com.spoonofcode.feature.scan.nfc.domain.repository.NFCTagRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import org.koin.dsl.module

val scanPresentationTestModule = module {
    includes(
        loggingModule,
        productDataTestModule,
        productDomainModule,
        scanDomainModule,
        scanPresentationModule,
    )
    single { getPlatform() }
    single<NFCTagRepository> {
        mock {
            everySuspend { getNFCTAg(any()) } returns Result.success(
                com.spoonofcode.feature.scan.nfc.domain.model.NFCTag(
                    publicId = com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1.id,
                    tagType = "nft",
                )
            )
        }
    }
}
