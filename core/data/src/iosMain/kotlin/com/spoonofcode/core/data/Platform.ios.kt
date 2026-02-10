package com.spoonofcode.core.data

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val platformType: PlatformType = PlatformType.iOS
}

actual fun getPlatform(): Platform = IOSPlatform()