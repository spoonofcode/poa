import AppKit
import CoreGraphics
import Foundation
import ImageIO
import UniformTypeIdentifiers

let inputURL = URL(fileURLWithPath: "/Users/spoonofcode/soc/projects/poa/iosApp/App Store Connect Assets/taggy-go-header-final-5244x2950.png")
let outputURL = URL(fileURLWithPath: "/Users/spoonofcode/soc/projects/poa/iosApp/App Store Connect Assets/taggy-go-search-results-3840x2560.png")

guard let image = NSImage(contentsOf: inputURL),
      let imageCG = image.cgImage(forProposedRect: nil, context: nil, hints: nil),
      let context = CGContext(
        data: nil,
        width: 3_840,
        height: 2_560,
        bitsPerComponent: 8,
        bytesPerRow: 0,
        space: CGColorSpaceCreateDeviceRGB(),
        bitmapInfo: CGImageAlphaInfo.noneSkipLast.rawValue
      )
else {
    fatalError("Unable to load the header asset")
}

context.setFillColor(CGColor(red: 0.06, green: 0.065, blue: 0.055, alpha: 1))
context.fill(CGRect(x: 0, y: 0, width: 3_840, height: 2_560))
context.interpolationQuality = .high
context.draw(imageCG, in: CGRect(x: 0, y: 200, width: 3_840, height: 2_160))

guard let outputImage = context.makeImage(),
      let destination = CGImageDestinationCreateWithURL(outputURL as CFURL, UTType.png.identifier as CFString, 1, nil)
else {
    fatalError("Unable to create search results asset")
}

CGImageDestinationAddImage(destination, outputImage, nil)
guard CGImageDestinationFinalize(destination) else {
    fatalError("Unable to save search results asset")
}
