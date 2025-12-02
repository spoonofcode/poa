package com.spoonofcode.poa.feature.profile

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.profile.di.profileTestModule
import com.spoonofcode.poa.feature.profile.edit.ProfileEditViewModel
import com.spoonofcode.poa.feature.profile.edit.ProfileEditViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileEditViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileEditViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(profileTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ProfileEditViewState(),
                actual = awaitItem()
            )
        }
    }
}