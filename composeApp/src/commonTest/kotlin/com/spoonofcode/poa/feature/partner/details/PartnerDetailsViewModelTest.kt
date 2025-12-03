package com.spoonofcode.poa.feature.partner.details

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.partner.di.partnerTestModule
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
        modules = arrayOf(partnerTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = PartnerDetailsViewState(),
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
                expected = PartnerDetailsViewState(
                    partner = PARTNER_1,
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }
}