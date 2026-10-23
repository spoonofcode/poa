package com.spoonofcode.core.recaptcha

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import platform.Foundation.NSUUID
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow
import platform.WebKit.WKScriptMessage
import platform.WebKit.WKScriptMessageHandlerProtocol
import platform.WebKit.WKUserContentController
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.darwin.NSObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal object RecaptchaWebViewRunner {
    @OptIn(ExperimentalForeignApi::class)
    suspend fun run(
        siteKey: String,
        action: String,
    ): String =
        withContext(Dispatchers.Main) {
            withTimeout(20000) {
                suspendCancellableCoroutine { cont ->
                    val handlerName = "recaptchaHandler_${NSUUID().UUIDString}"
                    val contentController = WKUserContentController()

                    val handler =
                        object : NSObject(), WKScriptMessageHandlerProtocol {
                            override fun userContentController(
                                userContentController: WKUserContentController,
                                didReceiveScriptMessage: WKScriptMessage,
                            ) {
                                val body = didReceiveScriptMessage.body?.toString()

                                userContentController.removeScriptMessageHandlerForName(handlerName)

                                if (body.isNullOrBlank() || body.startsWith("ERROR:")) {
                                    println("reCAPTCHA Error: $body")
                                    if (cont.isActive) cont.resumeWithException(IllegalStateException(body ?: "Empty token"))
                                } else {
                                    println("reCAPTCHA Token obtained (length: ${body.length}): ${body.take(10)}...")
                                    if (cont.isActive) cont.resume(body)
                                }
                            }
                        }

                    println("reCAPTCHA Running for siteKey: $siteKey, action: $action")

                    contentController.addScriptMessageHandler(handler, name = handlerName)

                    val config =
                        WKWebViewConfiguration().apply {
                            userContentController = contentController
                        }

                    val frame = platform.CoreGraphics.CGRectMake(0.0, 0.0, 1.0, 1.0)
                    val webView = WKWebView(frame = frame, configuration = config)
                    webView.alpha = 0.01
                    webView.hidden = false

                    val window = UIApplication.sharedApplication.windows.firstOrNull() as? UIWindow
                    if (window != null) {
                        window.addSubview(webView)
                    }

                    val html =
                        """
                        <!doctype html>
                        <html>
                          <head>
                            <meta charset="utf-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <script src="https://www.google.com/recaptcha/enterprise.js?render=$siteKey" 
                                    onerror="window.webkit.messageHandlers['$handlerName'].postMessage('ERROR:Script failed to load')"></script>
                            <script>
                              function run() {
                                try {
                                  if (typeof grecaptcha === 'undefined' || typeof grecaptcha.enterprise === 'undefined') {
                                    window.webkit.messageHandlers['$handlerName'].postMessage('ERROR:grecaptcha.enterprise is undefined');
                                    return;
                                  }
                                  grecaptcha.enterprise.ready(function() {
                                    try {
                                      grecaptcha.enterprise.execute('$siteKey', {action: '$action'}).then(function(token) {
                                        window.webkit.messageHandlers['$handlerName'].postMessage(token);
                                      }).catch(function(err) {
                                        window.webkit.messageHandlers['$handlerName'].postMessage('ERROR:Execution failed: ' + err);
                                      });
                                    } catch (e) {
                                      window.webkit.messageHandlers['$handlerName'].postMessage('ERROR:Execution crash: ' + e.message);
                                    }
                                  });
                                } catch (e) {
                                  window.webkit.messageHandlers['$handlerName'].postMessage('ERROR:Ready crash: ' + e.message);
                                }
                              }
                            </script>
                          </head>
                          <body onload="run()"></body>
                        </html>
                        """.trimIndent()

                    val baseURL = platform.Foundation.NSURL.URLWithString("https://www.google.com")
                    webView.loadHTMLString(html, baseURL = baseURL)

                    cont.invokeOnCancellation {
                        contentController.removeScriptMessageHandlerForName(handlerName)
                        webView.removeFromSuperview()
                    }
                }
            }
        }
}
