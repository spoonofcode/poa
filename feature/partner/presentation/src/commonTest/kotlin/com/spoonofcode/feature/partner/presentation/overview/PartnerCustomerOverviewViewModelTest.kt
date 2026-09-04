package com.spoonofcode.feature.partner.presentation.overview

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class PartnerCustomerOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerCustomerOverviewViewModel
    private lateinit var partnerRepository: PartnerRepository

//    @BeforeTest
//    override fun beforeTest() {
//        modules = arrayOf(partnerPresentationTestModule)
//        super.beforeTest()
//        partnerRepository = getKoin().get()
//        viewModel = getSut()
//    }
//
//    @AfterTest
//    override fun afterTest() {
//        super.afterTest()
//    }
//
//    @Test
//    fun `initial state is default`() = runTest {
//        viewModel.viewState.test {
//            assertEquals(
//                expected = ViewState(
//                    data = PartnerCustomerOverviewViewState(),
//                ),
//                actual = awaitItem()
//            )
//        }
//    }
//
//    @Test
//    fun `init view`() = runTest {
//        viewModel.viewState.test {
//            skipItems(1)
//
//            viewModel.onAction(
//                PartnerCustomerOverviewViewAction.InitView(
//                    partnerId = PARTNER_1.id,
//                )
//            )
//
//            assertEquals(
//                expected = ViewState(
//                    data = PartnerCustomerOverviewViewState(
//                        customers = PARTNER_USERS,
//                        checkedUserIds = emptySet(),
//                    ),
//                    screenState = ScreenState.CONTENT,
//                ),
//                actual = awaitItem()
//            )
//        }
//
//        verifySuspend {
//            partnerRepository.getPartnerUsers(PARTNER_1.id)
//        }
//    }
//
//    @Test
//    fun `checked customer`() = runTest {
//        viewModel.viewState.test {
//            skipItems(1)
//
//            viewModel.onAction(
//                PartnerCustomerOverviewViewAction.InitView(
//                    partnerId = PARTNER_1.id,
//                )
//            )
//
//            assertEquals(
//                expected = ViewState(
//                    data = PartnerCustomerOverviewViewState(
//                        customers = PARTNER_USERS,
//                        checkedUserIds = emptySet(),
//                    ),
//                    screenState = ScreenState.CONTENT,
//                ),
//                actual = awaitItem()
//            )
//
//            viewModel.onAction(
//                PartnerCustomerOverviewViewAction.CheckedCustomer(
//                    email = PARTNER_USER_1.id,
//                )
//            )
//
//            assertEquals(
//                expected = ViewState(
//                    data = PartnerCustomerOverviewViewState(
//                        customers = PARTNER_USERS,
//                        checkedUserIds = setOf(PARTNER_USER_1.id),
//                    ),
//                    screenState = ScreenState.CONTENT,
//                ),
//                actual = awaitItem()
//            )
//        }
//    }
//
//    @Test
//    fun `navigates to notification screen`() = runTest {
//        viewModel.onAction(PartnerCustomerOverviewViewAction.NavigateToNotification)
//        advanceUntilIdle()
//
//        verifySuspend { routeResolver.resolve(NotificationModuleRoute.NotificationEdit()) }
//        verifySuspend { viewModelNavigator.push(any()) }
//    }
}