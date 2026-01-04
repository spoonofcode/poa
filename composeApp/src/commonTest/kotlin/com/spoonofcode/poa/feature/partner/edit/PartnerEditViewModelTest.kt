package com.spoonofcode.poa.feature.partner.edit

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.core.data.repository.PartnerDataSource
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

    private lateinit var viewModel: com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewModel
    private lateinit var partnerDataSource: PartnerDataSource

    @BeforeTest
    override fun setup() {
        modules = arrayOf(partnerTestModule)
        super.setup()
        partnerDataSource = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.ChangeName(name))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.ChangeDescription(description))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.ChangeWebsiteLink(websiteLink))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.ChangeInstagramLink(instagramLink))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.ChangeFacebookLink(facebookLink))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.ChangeYoutubeLink(youtubeLink))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.ChangeXLink(xLink))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.InitView(partnerId = PARTNER_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.CONTENT,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction.SaveChanges)

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewState(
                    screenState = ScreenState.LOADING,
                    partner = PARTNER_1,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            partnerDataSource.update(any(), any())
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}