package com.spoonofcode.feature.auth.presentation

import com.spoonofcode.feature.auth.domain.repository.LoginRepository
import com.spoonofcode.feature.auth.presentation.LoginMockData.LOGIN_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun loginRepositoryMock() = mock<LoginRepository> {
    everySuspend { loginWithCode(any(), any()) } returns Result.success(LOGIN_1)
    everySuspend { loginWithGoogle(any()) } returns Result.success(LOGIN_1)
    everySuspend { requestCode(any(), any()) } returns Result.success(Unit)
}