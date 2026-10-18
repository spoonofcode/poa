package com.spoonofcode.feature.partner.presentation.edit

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PARTNER_1
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.spoonofcode.core.presentation.Res as CoreRes

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

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            with(viewState.awaitContentState()) {
                assertEquals(PARTNER_1, partner)
            }
        }
    }

    @Test
    fun `initial view - error`() = runTest {
        everySuspend { partnerRepository.getPartner(any()) } returns Result.failure(
            Exception("Test error")
        )

        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            viewState.awaitErrorState()
        }
    }

    @Test
    fun `change name`() = runTest {
        val newName = "New Partner Name"

        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.ChangeName(name = newName))

            with(viewState.awaitContentState()) {
                assertEquals(newName, partner.name)
            }
        }
    }

    @Test
    fun `change description`() = runTest {
        val newDescription = "New Description"

        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.ChangeDescription(description = newDescription))

            with(viewState.awaitContentState()) {
                assertEquals(newDescription, partner.description)
            }
        }
    }

    @Test
    fun `change website link`() = runTest {
        val newLink = "https://newwebsite.com"

        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.ChangeWebsiteLink(link = newLink))

            with(viewState.awaitContentState()) {
                assertEquals(newLink, partner.websiteLink)
            }
        }
    }

    @Test
    fun `change instagram link`() = runTest {
        val newLink = "https://instagram.com/new"

        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.ChangeInstagramLink(link = newLink))

            with(viewState.awaitContentState()) {
                assertEquals(newLink, partner.instagramLink)
            }
        }
    }

    @Test
    fun `change facebook link`() = runTest {
        val newLink = "https://facebook.com/new"

        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.ChangeFacebookLink(link = newLink))

            with(viewState.awaitContentState()) {
                assertEquals(newLink, partner.facebookLink)
            }
        }
    }

    @Test
    fun `change youtube link`() = runTest {
        val newLink = "https://youtube.com/new"

        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.ChangeYoutubeLink(link = newLink))

            with(viewState.awaitContentState()) {
                assertEquals(newLink, partner.youtubeLink)
            }
        }
    }

    @Test
    fun `change x link`() = runTest {
        val newLink = "https://x.com/new"

        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.ChangeXLink(link = newLink))

            with(viewState.awaitContentState()) {
                assertEquals(newLink, partner.xLink)
            }
        }
    }

    @Test
    fun `save changes success`() = runTest {
        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.SaveChanges)

            with(viewState.awaitContentState()) {
                assertTrue(isSaveChangesButtonLoading)
            }

            verifySuspend {
                partnerRepository.updatePartner(
                    partnerId = PARTNER_1.id,
                    name = PARTNER_1.name,
                    partnerCategory = PARTNER_1.partnerCategory,
                    email = PARTNER_1.email,
                    description = PARTNER_1.description,
                    imageLink = PARTNER_1.imageLink,
                    websiteLink = PARTNER_1.websiteLink,
                    instagramLink = PARTNER_1.instagramLink,
                    facebookLink = PARTNER_1.facebookLink,
                    youtubeLink = PARTNER_1.youtubeLink,
                    xLink = PARTNER_1.xLink,
                )
            }
            verifySuspend { viewModelNavigator.pop() }
        }
    }

    @Test
    fun `save changes error`() = runTest {
        everySuspend {
            partnerRepository.updatePartner(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
            )
        } returns Result.failure(Exception("Error updating partner"))

        val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)

        runVmTest(viewModel) {
            onAction(PartnerEditViewAction.SaveChanges)

            with(viewState.awaitContentState()) {
                assertTrue(isSaveChangesButtonLoading)
            }
            with(viewState.awaitContentState()) {
                assertFalse(isSaveChangesButtonLoading)
            }
            assertEquals(
                expected = expectedErrorMessage,
                actual = snackbarEvent.awaitItem().message
            )

            verifySuspend {
                partnerRepository.updatePartner(
                    partnerId = PARTNER_1.id,
                    name = PARTNER_1.name,
                    partnerCategory = PARTNER_1.partnerCategory,
                    email = PARTNER_1.email,
                    description = PARTNER_1.description,
                    imageLink = PARTNER_1.imageLink,
                    websiteLink = PARTNER_1.websiteLink,
                    instagramLink = PARTNER_1.instagramLink,
                    facebookLink = PARTNER_1.facebookLink,
                    youtubeLink = PARTNER_1.youtubeLink,
                    xLink = PARTNER_1.xLink,
                )
            }
        }
    }
}
