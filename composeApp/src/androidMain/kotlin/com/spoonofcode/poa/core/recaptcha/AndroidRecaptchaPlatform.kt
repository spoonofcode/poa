package com.spoonofcode.poa.core.recaptcha

import android.app.Application
import com.google.android.recaptcha.Recaptcha
import com.google.android.recaptcha.RecaptchaAction
import com.google.android.recaptcha.RecaptchaException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AndroidRecaptchaPlatform(
    private val application: Application,
    private val siteKey: String,
) : RecaptchaPlatform {

    private lateinit var recaptchaClient: com.google.android.recaptcha.RecaptchaClient

    // we recommend initializing in a ViewModel
    private val recaptchaScope = CoroutineScope(Dispatchers.IO)

    init {
        initializeWithFetchClient()
    }

    override suspend fun execute(action: String): String {
//        println("BARTEK 1")
//        val client = Recaptcha.getClient(application, siteKey).getOrThrow()
//        println("BARTEK 2")
//        val result = client.execute(RecaptchaAction.custom(RecaptchaAction.LOGIN.action)).getOrThrow()
//        println("BARTEK 3")

        println("BARTEK 1")
        executeLoginAction()
        println("BARTEK 2")
        return "TEST"
    }

    private fun initializeWithFetchClient() {
        recaptchaScope.launch {
            try {
                recaptchaClient = Recaptcha.fetchClient(application, siteKey)
            } catch (e: RecaptchaException) {
                // Handle errors ...
            }
        }
    }

    private fun executeLoginAction() {
        recaptchaScope.launch {
            recaptchaClient
                .execute(RecaptchaAction.LOGIN)
                .onSuccess { token ->
                    token
                    println("BARTEK TOKEN = $token")
                    // Handle success ...
                    // See "What's next" section for instructions
                    // about handling tokens.
                }
                .onFailure { exception ->
                    println("BARTEK EXCEPTION = $exception")
                    // Handle errors ...
                }
        }
    }
}
