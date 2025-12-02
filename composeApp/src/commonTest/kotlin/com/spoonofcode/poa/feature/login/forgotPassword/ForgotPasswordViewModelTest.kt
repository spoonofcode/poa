package com.spoonofcode.poa.feature.login.forgotPassword

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.login.di.loginTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ForgotPasswordViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: ForgotPasswordViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(loginTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ForgotPasswordViewState(),
                actual = awaitItem()
            )
        }
    }
}