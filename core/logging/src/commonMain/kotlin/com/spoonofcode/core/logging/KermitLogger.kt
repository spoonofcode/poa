package com.spoonofcode.core.logging

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity

object KermitLogger : POALogger {
    init {
        if (!BuildKonfig.ENABLE_LOGGING) {
            Logger.setMinSeverity(Severity.Assert)
        } else {
            Logger.setMinSeverity(Severity.Verbose)
        }
    }

    override fun debug(message: String) {
        if (BuildKonfig.ENABLE_LOGGING) {
            Logger.d(message)
        }
    }

    override fun info(message: String) {
        if (BuildKonfig.ENABLE_LOGGING) {
            Logger.i(message)
        }
    }

    override fun warn(message: String) {
        if (BuildKonfig.ENABLE_LOGGING) {
            Logger.w(message)
        }
    }

    override fun error(
        message: String,
        throwable: Throwable?,
    ) {
        Logger.e(message, throwable)
    }
}
