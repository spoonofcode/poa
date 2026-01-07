package com.spoonofcode.poa.core.recaptcha

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSUUID
import platform.WebKit.*
import platform.darwin.NSObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue

internal object RecaptchaWebViewRunner {

    @OptIn(ExperimentalForeignApi::class)
    suspend fun run(siteKey: String, action: String): String =
        suspendCancellableCoroutine { cont ->

            val handlerName = "recaptchaHandler_${NSUUID().UUIDString}"

            val contentController = WKUserContentController()

            val handler = object : NSObject(), WKScriptMessageHandlerProtocol {
                override fun userContentController(
                    userContentController: WKUserContentController,
                    didReceiveScriptMessage: WKScriptMessage
                ) {
                    val token = didReceiveScriptMessage.body?.toString()
                    if (token.isNullOrBlank() || token.startsWith("ERROR:")) {
                        cont.resumeWithException(IllegalStateException(token ?: "Empty token"))
                    } else {
                        cont.resume(token)
                    }
                }
            }

            contentController.addScriptMessageHandler(handler, name = handlerName)

            val config = WKWebViewConfiguration().apply {
                userContentController = contentController
            }

            val webView = WKWebView(frame = platform.CoreGraphics.CGRectZero.readValue(), configuration = config)

            val html = """
                <!doctype html>
                <html>
                  <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <script src="https://www.google.com/recaptcha/api.js?render=$siteKey"></script>
                    <script>
                      function run() {
                        grecaptcha.ready(function() {
                          grecaptcha.execute('$siteKey', {action: '$action'}).then(function(token) {
                            window.webkit.messageHandlers['$handlerName'].postMessage(token);
                          }).catch(function(err) {
                            window.webkit.messageHandlers['$handlerName'].postMessage('ERROR:' + err);
                          });
                        });
                      }
                    </script>
                  </head>
                  <body onload="run()"></body>
                </html>
            """.trimIndent()

            webView.loadHTMLString(html, baseURL = null)

            cont.invokeOnCancellation {
                try { contentController.removeScriptMessageHandlerForName(handlerName) } catch (_: Throwable) {}
            }
        }
}
