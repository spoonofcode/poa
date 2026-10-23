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
import com.spoonofcode.feature.partner.presentation.PartnerEvent
import com.spoonofcode.feature.partner.presentation.PartnerEventBus
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
    fun `initial view`() =
        runTest {
            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals(PARTNER_1, partner)
                }
            }
        }

    @Test
    fun `initial view - error`() =
        runTest {
            everySuspend { partnerRepository.getPartner(any()) } returns
                Result.failure(
                    Exception("Test error"),
                )

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                viewState.awaitErrorState()
            }
        }

    @Test
    fun `change name`() =
        runTest {
            val newName = "New Partner Name"

            runVmTest(viewModel) {
                onAction(PartnerEditViewAction.ChangeName(name = newName))

                with(viewState.awaitContentState()) {
                    assertEquals(newName, partner.name)
                    assertTrue(isSaveChangesButtonEnabled)
                }
            }
        }

    @Test
    fun `change name to empty - save changes button disabled`() =
        runTest {
            runVmTest(viewModel) {
                onAction(PartnerEditViewAction.ChangeName(name = ""))

                with(viewState.awaitContentState()) {
                    assertEquals("", partner.name)
                    assertFalse(isSaveChangesButtonEnabled)
                }
            }
        }

    @Test
    fun `change description`() =
        runTest {
            val newDescription = "New Description"

            runVmTest(viewModel) {
                onAction(PartnerEditViewAction.ChangeDescription(description = newDescription))

                with(viewState.awaitContentState()) {
                    assertEquals(newDescription, partner.description)
                }
            }
        }

    @Test
    fun `change website link`() =
        runTest {
            val newLink = "https://newwebsite.com"

            runVmTest(viewModel) {
                onAction(PartnerEditViewAction.ChangeWebsiteLink(link = newLink))

                with(viewState.awaitContentState()) {
                    assertEquals(newLink, partner.websiteLink)
                }
            }
        }

    @Test
    fun `change instagram link`() =
        runTest {
            val newLink = "https://instagram.com/new"

            runVmTest(viewModel) {
                onAction(PartnerEditViewAction.ChangeInstagramLink(link = newLink))

                with(viewState.awaitContentState()) {
                    assertEquals(newLink, partner.instagramLink)
                }
            }
        }

    @Test
    fun `change facebook link`() =
        runTest {
            val newLink = "https://facebook.com/new"

            runVmTest(viewModel) {
                onAction(PartnerEditViewAction.ChangeFacebookLink(link = newLink))

                with(viewState.awaitContentState()) {
                    assertEquals(newLink, partner.facebookLink)
                }
            }
        }

    @Test
    fun `change youtube link`() =
        runTest {
            val newLink = "https://youtube.com/new"

            runVmTest(viewModel) {
                onAction(PartnerEditViewAction.ChangeYoutubeLink(link = newLink))

                with(viewState.awaitContentState()) {
                    assertEquals(newLink, partner.youtubeLink)
                }
            }
        }

    @Test
    fun `change x link`() =
        runTest {
            val newLink = "https://x.com/new"

            runVmTest(viewModel) {
                onAction(PartnerEditViewAction.ChangeXLink(link = newLink))

                with(viewState.awaitContentState()) {
                    assertEquals(newLink, partner.xLink)
                }
            }
        }

    @Test
    fun `save changes success`() =
        runTest {
            val partnerEventBus: PartnerEventBus = getKoin().get()

            runVmTest(viewModel) {
                val partnerUpdatedEvents = partnerEventBus.partnerUpdatedEvents.test()

                onAction(PartnerEditViewAction.SaveChanges)

                with(viewState.awaitContentState()) {
                    assertTrue(isSaveChangesButtonLoading)
                    assertFalse(isViewEnable)
                }

                assertEquals(PartnerEvent.PartnerUpdated, partnerUpdatedEvents.awaitItem())

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
    fun `save changes with updated fields success`() =
        runTest {
            val partnerEventBus: PartnerEventBus = getKoin().get()
            val newName = "Updated Name"
            val newDescription = "Updated Description"
            val newWebsiteLink = "https://updated-website.com"
            val newInstagramLink = "https://instagram.com/updated"
            val newFacebookLink = "https://facebook.com/updated"
            val newYoutubeLink = "https://youtube.com/updated"
            val newXLink = "https://x.com/updated"

            runVmTest(viewModel) {
                val partnerUpdatedEvents = partnerEventBus.partnerUpdatedEvents.test()

                onAction(PartnerEditViewAction.ChangeName(name = newName))
                with(viewState.awaitContentState()) {
                    assertEquals(newName, partner.name)
                }

                onAction(PartnerEditViewAction.ChangeDescription(description = newDescription))
                with(viewState.awaitContentState()) {
                    assertEquals(newDescription, partner.description)
                }

                onAction(PartnerEditViewAction.ChangeWebsiteLink(link = newWebsiteLink))
                with(viewState.awaitContentState()) {
                    assertEquals(newWebsiteLink, partner.websiteLink)
                }

                onAction(PartnerEditViewAction.ChangeInstagramLink(link = newInstagramLink))
                with(viewState.awaitContentState()) {
                    assertEquals(newInstagramLink, partner.instagramLink)
                }

                onAction(PartnerEditViewAction.ChangeFacebookLink(link = newFacebookLink))
                with(viewState.awaitContentState()) {
                    assertEquals(newFacebookLink, partner.facebookLink)
                }

                onAction(PartnerEditViewAction.ChangeYoutubeLink(link = newYoutubeLink))
                with(viewState.awaitContentState()) {
                    assertEquals(newYoutubeLink, partner.youtubeLink)
                }

                onAction(PartnerEditViewAction.ChangeXLink(link = newXLink))
                with(viewState.awaitContentState()) {
                    assertEquals(newXLink, partner.xLink)
                }

                onAction(PartnerEditViewAction.SaveChanges)
                with(viewState.awaitContentState()) {
                    assertTrue(isSaveChangesButtonLoading)
                    assertFalse(isViewEnable)
                }

                assertEquals(PartnerEvent.PartnerUpdated, partnerUpdatedEvents.awaitItem())

                verifySuspend {
                    partnerRepository.updatePartner(
                        partnerId = PARTNER_1.id,
                        name = newName,
                        partnerCategory = PARTNER_1.partnerCategory,
                        email = PARTNER_1.email,
                        description = newDescription,
                        imageLink = PARTNER_1.imageLink,
                        websiteLink = newWebsiteLink,
                        instagramLink = newInstagramLink,
                        facebookLink = newFacebookLink,
                        youtubeLink = newYoutubeLink,
                        xLink = newXLink,
                    )
                }
                verifySuspend { viewModelNavigator.pop() }
            }
        }

    @Test
    fun `save changes error`() =
        runTest {
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
                    assertFalse(isViewEnable)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isSaveChangesButtonLoading)
                    assertTrue(isViewEnable)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
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
