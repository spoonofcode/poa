package com.spoonofcode.feature.partner.presentation.overview

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.data.test.PartnerMockData.CUSTOMERS
import com.spoonofcode.feature.partner.data.test.PartnerMockData.CUSTOMER_1
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewAction
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PartnerCustomerOverviewViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: PartnerCustomerOverviewViewModel
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

    @Test
    fun `initial view`() =
        runTest {
            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals(CUSTOMERS, customers)
                    assertTrue(checkedUserIds.isEmpty())
                }
            }
        }

    @Test
    fun `initial view - error`() =
        runTest {
            everySuspend { partnerRepository.getPartnerCustomers() } returns
                Result.failure(
                    Exception("Test error"),
                )

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                viewState.awaitErrorState()
            }
        }

    @Test
    fun `checked customer`() =
        runTest {
            runVmTest(viewModel) {
                onAction(PartnerCustomerOverviewViewAction.CheckedCustomer(id = CUSTOMER_1.id))

                with(viewState.awaitContentState()) {
                    assertEquals(setOf(CUSTOMER_1.id), checkedUserIds)
                }

                onAction(PartnerCustomerOverviewViewAction.CheckedCustomer(id = CUSTOMER_1.id))

                with(viewState.awaitContentState()) {
                    assertTrue(checkedUserIds.isEmpty())
                }
            }
        }

    @Test
    fun `navigates to notification screen`() =
        runTest {
            runVmTest(viewModel) {
                onAction(PartnerCustomerOverviewViewAction.CheckedCustomer(id = CUSTOMER_1.id))
                with(viewState.awaitContentState()) {
                    assertEquals(setOf(CUSTOMER_1.id), checkedUserIds)
                }

                onAction(PartnerCustomerOverviewViewAction.NavigateToNotification)

                verifySuspend {
                    viewModelNavigator.push(
                        PartnerModuleRoute.PartnerNotificationEdit(
                            selectedTopics = setOf(PartnerNotificationType.USER.createTopic(CUSTOMER_1.id)),
                            selectedNames = setOf(CUSTOMER_1.name),
                        ),
                    )
                }
            }
        }
}
