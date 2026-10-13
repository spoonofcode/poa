package com.spoonofcode.feature.partner.presentation.edit

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class PartnerEditViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerEditViewModel
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerPresentationTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

}