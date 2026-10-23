package com.spoonofcode.feature.scan.nfc.domain.di

import com.spoonofcode.feature.scan.nfc.domain.model.NFCTag
import com.spoonofcode.feature.scan.nfc.domain.repository.NFCTagRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import org.koin.dsl.module

val scanDomainTestModule =
    module {
        includes(
            scanDomainModule,
        )
        single<NFCTagRepository> {
            mock {
                everySuspend { getNFCTAg(any()) } returns
                    Result.success(
                        NFCTag(
                            publicId = "1",
                            tagType = "nft",
                        ),
                    )
            }
        }
    }
