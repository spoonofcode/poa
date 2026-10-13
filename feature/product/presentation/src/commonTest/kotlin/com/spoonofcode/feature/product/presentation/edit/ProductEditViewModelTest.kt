package com.spoonofcode.feature.product.presentation.edit

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class ProductEditViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProductEditViewModel
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productPresentationTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

}