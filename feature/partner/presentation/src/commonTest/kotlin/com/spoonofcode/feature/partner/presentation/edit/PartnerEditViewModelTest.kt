package com.spoonofcode.feature.partner.presentation.edit

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.partner.presentation.PartnerMockData.PARTNER_1
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
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

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(partner = PARTNER_1),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change name`() = runTest {
        val name = "New name"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1,
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeName(name))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1.copy(name = name),
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change description`() = runTest {
        val description = "New description"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeDescription(description))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1.copy(description = description),
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change website link`() = runTest {
        val websiteLink = "New website link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeWebsiteLink(websiteLink))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1.copy(websiteLink = websiteLink),
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change instagram link`() = runTest {
        val instagramLink = "New instagram link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeInstagramLink(instagramLink))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1.copy(instagramLink = instagramLink),
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change facebook link`() = runTest {
        val facebookLink = "New facebook link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeFacebookLink(facebookLink))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1.copy(facebookLink = facebookLink),
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change youtube link`() = runTest {
        val youtubeLink = "New youtube link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeYoutubeLink(youtubeLink))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1.copy(youtubeLink = youtubeLink),
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change x link`() = runTest {
        val xLink = "New x link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeXLink(xLink))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1.copy(xLink = xLink),
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `save changes`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1
                    ),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.SaveChanges)

            assertEquals(
                expected = ViewState(
                    data = PartnerEditViewState(
                        partner = PARTNER_1
                    ),
                    screenState = ScreenState.LOADING
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            partnerRepository.updatePartner(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
            )
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}