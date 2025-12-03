package com.spoonofcode.poa.feature.partner.edit

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.partner.di.partnerTestModule
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
    override fun setup() {
        modules = arrayOf(partnerTestModule)
        super.setup()
        partnerRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = PartnerEditViewState(),
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeName(name))

            assertEquals(
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1.copy(name = name),
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeDescription(description))

            assertEquals(
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1.copy(description = description),
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeWebsiteLink(websiteLink))

            assertEquals(
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1.copy(websiteLink = websiteLink),
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeInstagramLink(instagramLink))

            assertEquals(
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1.copy(instagramLink = instagramLink),
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeFacebookLink(facebookLink))

            assertEquals(
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1.copy(facebookLink = facebookLink),
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeYoutubeLink(youtubeLink))

            assertEquals(
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1.copy(youtubeLink = youtubeLink),
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.ChangeXLink(xLink))

            assertEquals(
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1.copy(xLink = xLink),
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
                expected = PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(PartnerEditViewAction.SaveChanges)

            assertEquals(
                expected = PartnerEditViewState(
                    screenState = ScreenState.LOADING,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            partnerRepository.update(any(), any())
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}