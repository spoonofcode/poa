package com.spoonofcode.feature.partner.presentation.details

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.feature.partner.presentation.PartnerMockData.PARTNER_1
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PartnerDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerDetailsViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(partnerPresentationTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = PartnerDetailsViewState(),

                    ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerDetailsViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerDetailsViewState(partner = PARTNER_1),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }
}