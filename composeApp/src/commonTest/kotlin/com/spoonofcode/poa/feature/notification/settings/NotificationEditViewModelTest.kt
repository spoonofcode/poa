package com.spoonofcode.poa.feature.notification.settings


import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.feature.notification.di.notificationTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationSettingsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NotificationSettingsViewModel
    private lateinit var productRepository: ProductRepository
    private lateinit var userRepository: UserRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(notificationTestModule)
        super.setup()
        productRepository = getKoin().get()
        userRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = NotificationSettingsViewState(),
                actual = awaitItem()
            )
        }
    }
}