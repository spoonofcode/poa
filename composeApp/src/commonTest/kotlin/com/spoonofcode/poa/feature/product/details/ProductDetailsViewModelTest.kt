package com.spoonofcode.poa.feature.product.details

import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.product.di.productTestModule
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProductDetailsViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(
            productTestModule,
        )
        super.setup()
    }

    @Test
    fun `product addition successful`() = runTest {
        viewModel = getSut()

        viewModel.onAction(ProductDetailsViewAction.AddProduct)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.pop() }
    }

//    @Test
//    fun `init view`() = runTest {
//        viewModel = getSut()
//
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                ProfileViewState(
//                    profile = Profile(
//                        name = "Profile name 1",
//                        numberOfEventsUserParticipatedIn = 1,
//                        numberOfEventsCreatedByUser = 1
//                    ),
//                    isLoadingView = false
//                ),
//                awaitItem()
//            )
//        }
//    }
//
//    @Test
//    fun `init view with error`() = runTest {
//        everySuspend { profileRepository.read(any()) } throws Exception("test exception")
//
//        viewModel = getSut()
//
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                ProfileViewState(
//                    profile = null,
//                    isLoadingView = false,
//                    isErrorView = true,
//                ),
//                awaitItem()
//            )
//        }
//    }


}