package com.spoonofcode.core.recaptcha

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun recaptchaMock() = mock<RecaptchaClient> {
    everySuspend { execute(any()) } returns "test recaptcha token"
}