package com.spoonofcode.feature.partner.presentation.overview

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class PartnerOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerOverviewViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerPresentationTestModule)
        super.beforeTest()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }
}