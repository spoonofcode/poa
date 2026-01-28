package com.spoonofcode.poa.feature.partner.details

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.feature.partner.di.partnerTestModule
import com.spoonofcode.poa.feature.partner.presentation.details.PartnerDetailsViewAction
import com.spoonofcode.poa.feature.partner.presentation.details.PartnerDetailsViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PartnerDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: com.spoonofcode.poa.feature.partner.presentation.details.PartnerDetailsViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(partnerTestModule)
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