package com.spoonofcode.core.recaptcha

import cocoapods.RecaptchaEnterpriseSDK.Recaptcha
import cocoapods.RecaptchaEnterpriseSDK.RecaptchaAction
import cocoapods.RecaptchaEnterpriseSDK.RecaptchaClient
import com.spoonofcode.core.logging.POALogger
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSBundle
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalForeignApi::class)
class IosRecaptchaPlatform(
    private val siteKey: String,
    private val poaLogger: POALogger,
) : RecaptchaPlatform {
    private var client: RecaptchaClient? = null

    override suspend fun execute(action: String): String {
        val bundleId = NSBundle.mainBundle.bundleIdentifier
        poaLogger.debug("IosRecaptchaPlatform: Executing action '$action' with siteKey=$siteKey, bundleId=$bundleId")
        val activeClient = getOrFetchClient()
        return executeAction(activeClient, action)
    }

    private suspend fun getOrFetchClient(): RecaptchaClient {
        client?.let { return it }
        val bundleId = NSBundle.mainBundle.bundleIdentifier
        poaLogger.debug("IosRecaptchaPlatform: Initializing client with siteKey=$siteKey, bundleId=$bundleId")
        return suspendCancellableCoroutine { continuation ->
            Recaptcha.fetchClientWithSiteKey(siteKey) { fetchedClient, error ->
                if (error != null) {
                    poaLogger.error(
                        "IosRecaptchaPlatform: Failed to initialize reCAPTCHA (siteKey=$siteKey, bundleId=$bundleId): ${error.localizedDescription}",
                    )
                    continuation.resumeWithException(
                        IllegalStateException("Failed to initialize reCAPTCHA: ${error.localizedDescription}"),
                    )
                } else if (fetchedClient != null) {
                    poaLogger.debug("IosRecaptchaPlatform: Client initialized successfully")
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
            val bundleId = NSBundle.mainBundle.bundleIdentifier
            val recaptchaAction = RecaptchaAction.custom(action)
            recaptchaClient.executeWithAction(recaptchaAction) { token, error ->
                if (error != null) {
                    poaLogger.error(
                        "IosRecaptchaPlatform: Failed to execute reCAPTCHA action (siteKey=$siteKey, bundleId=$bundleId): ${error.localizedDescription}",
                    )
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
