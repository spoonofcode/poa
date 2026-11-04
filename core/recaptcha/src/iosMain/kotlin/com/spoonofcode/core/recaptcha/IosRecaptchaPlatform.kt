package com.spoonofcode.core.recaptcha

import cocoapods.RecaptchaEnterpriseSDK.Recaptcha
import cocoapods.RecaptchaEnterpriseSDK.RecaptchaAction
import cocoapods.RecaptchaEnterpriseSDK.RecaptchaClient
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalForeignApi::class)
class IosRecaptchaPlatform(
    private val siteKey: String,
) : RecaptchaPlatform {
    private var client: RecaptchaClient? = null

    override suspend fun execute(action: String): String {
        val activeClient = getOrFetchClient()
        return executeAction(activeClient, action)
    }

    private suspend fun getOrFetchClient(): RecaptchaClient {
        client?.let { return it }
        return suspendCancellableCoroutine { continuation ->
            Recaptcha.fetchClientWithSiteKey(siteKey) { fetchedClient, error ->
                if (error != null) {
                    continuation.resumeWithException(
                        IllegalStateException("Failed to initialize reCAPTCHA: ${error.localizedDescription}"),
                    )
                } else if (fetchedClient != null) {
                    client = fetchedClient
                    continuation.resume(fetchedClient)
                } else {
                    continuation.resumeWithException(
                        IllegalStateException("Failed to initialize reCAPTCHA: client is null"),
                    )
                }
            }
        }
    }

    private suspend fun executeAction(
        recaptchaClient: RecaptchaClient,
        action: String,
    ): String =
        suspendCancellableCoroutine { continuation ->
            val recaptchaAction = RecaptchaAction.custom(action)
            recaptchaClient.executeWithAction(recaptchaAction) { token, error ->
                if (error != null) {
                    continuation.resumeWithException(
                        IllegalStateException("Failed to execute reCAPTCHA action: ${error.localizedDescription}"),
                    )
                } else if (token != null) {
                    continuation.resume(token)
                } else {
                    continuation.resumeWithException(
                        IllegalStateException("reCAPTCHA token is null"),
                    )
                }
            }
        }
}
