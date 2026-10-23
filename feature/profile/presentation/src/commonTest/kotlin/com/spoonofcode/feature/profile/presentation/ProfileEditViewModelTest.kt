package com.spoonofcode.feature.profile.presentation

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import com.spoonofcode.feature.profile.presentation.di.profilePresentationTestModule
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class ProfileEditViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileEditViewModel
    private lateinit var profileRepository: ProfileRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(profilePresentationTestModule)
        super.beforeTest()
        profileRepository = getKoin().get()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

}