package com.spoonofcode.core.presentation.compose

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.annotation.DelicateCoilApi
import coil3.asImage
import coil3.decode.DecodeResult
import coil3.decode.Decoder
import coil3.fetch.SourceFetchResult
import coil3.request.Options
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorInfo
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGColorSpaceRelease
import platform.CoreGraphics.CGContextDrawImage
import platform.CoreGraphics.CGContextRelease
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageGetHeight
import platform.CoreGraphics.CGImageGetWidth
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.kCGBitmapByteOrder32Big
import platform.Foundation.NSData
import platform.Foundation.create
import platform.UIKit.UIImage

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
class IosImageDecoder(
    private val source: SourceFetchResult,
) : Decoder {

    override suspend fun decode(): DecodeResult? {
        val bytes = source.source.source().readByteArray()
        if (bytes.isEmpty()) return null

        val nsData = bytes.usePinned { pinned ->
            NSData.create(bytes = pinned.addressOf(0), length = bytes.size.toULong())
        }

        val uiImage = UIImage.imageWithData(nsData) ?: return null
        val cgImage = uiImage.CGImage ?: return null

        val width = CGImageGetWidth(cgImage).toInt()
        val height = CGImageGetHeight(cgImage).toInt()
        if ((width <= 0) || (height <= 0)) return null

        val colorSpace = CGColorSpaceCreateDeviceRGB()
        val bytesPerPixel = 4
        val bytesPerRow = bytesPerPixel * width
        val rawData = ByteArray(bytesPerRow * height)

        val createdContext = rawData.usePinned { pinned ->
            CGBitmapContextCreate(
                data = pinned.addressOf(0),
                width = width.toULong(),
                height = height.toULong(),
                bitsPerComponent = 8u,
                bytesPerRow = bytesPerRow.toULong(),
                space = colorSpace,
                bitmapInfo = CGImageAlphaInfo.kCGImageAlphaPremultipliedLast.value or kCGBitmapByteOrder32Big,
            )
        } ?: run {
            CGColorSpaceRelease(colorSpace)
            return null
        }

        CGContextDrawImage(
            createdContext,
            CGRectMake(0.0, 0.0, width.toDouble(), height.toDouble()),
            cgImage,
        )
        CGContextRelease(createdContext)
        CGColorSpaceRelease(colorSpace)

        val skiaImage = Image.makeRaster(
            imageInfo = ImageInfo(
                colorInfo = ColorInfo(
                    colorType = ColorType.RGBA_8888,
                    alphaType = ColorAlphaType.PREMUL,
                    colorSpace = null,
                ),
                width = width,
                height = height,
            ),
            bytes = rawData,
            rowBytes = bytesPerRow,
        )

        val bitmap = Bitmap.makeFromImage(skiaImage)

        return DecodeResult(
            image = bitmap.asImage(),
            isSampled = false,
        )
    }

    class Factory : Decoder.Factory {
        override fun create(
            result: SourceFetchResult,
            options: Options,
            imageLoader: ImageLoader,
        ): Decoder = IosImageDecoder(result)
    }
}

@OptIn(DelicateCoilApi::class)
fun setupIosCoilImageLoader() {
    SingletonImageLoader.setUnsafe(
        ImageLoader.Builder(PlatformContext.INSTANCE)
            .components {
                add(IosImageDecoder.Factory())
            }
            .build(),
    )
}
