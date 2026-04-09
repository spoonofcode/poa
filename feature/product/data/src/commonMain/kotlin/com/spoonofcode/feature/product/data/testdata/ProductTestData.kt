package com.spoonofcode.feature.product.data.testdata

import com.spoonofcode.feature.product.domain.model.Product
import kotlinx.datetime.LocalDateTime

val PARTNER_1 = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
val PARTNER_2 = "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"

val PRODUCTS: List<Product> = listOf(
    Product(
        id = "1",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product #1",
        description = "Przykładowy opis wydarzenia nr 1",
        seriesId = "1",
        seriesName = "Series 1",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_261/bluza-damska-z-kapturem-ocieplana-bordo.webp",
        customLink = "https://beautysaute.pl/",
        partnerId = PARTNER_1,
    ),
    Product(
        id = "2",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product #2",
        description = "Przykładowy opis wydarzenia nr 2",
        seriesId = "2",
        seriesName = "Series 2",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_267/komplet-mocca-post.webp",
        customLink = "https://beautysaute.pl/",
        partnerId = PARTNER_1,
    ),
    Product(
        id = "3",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product #3",
        description = "Przykładowy opis wydarzenia nr 3",
        seriesId = "3",
        seriesName = "Series 3",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_259/Bluza-z-kapturem-damska-czarna-ocieplana.webp",
        customLink = "https://beautysaute.pl/",
        partnerId = PARTNER_2,
    ),

    Product(
        id = "4",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product #4",
        description = "Przykładowy opis wydarzenia nr 4",
        seriesId = "4",
        seriesName = "Series 4",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_257/bluza-damska-z-kapturem-pudrowy-roz.webp",
        customLink = "https://beautysaute.pl/",
        partnerId = PARTNER_2,
    ),
    Product(
        id = "5",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product #5",
        description = "Przykładowy opis wydarzenia nr 5",
        seriesId = "5",
        seriesName = "Series 5",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_262/Bluza-damska-bez-kaptura-czekolada.webp",
        customLink = "https://beautysaute.pl/",
        partnerId = PARTNER_1,
    ),

    Product(
        id = "6",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product 997",
        description = "First unique T-shirt collection by Proof of Wear!",
        seriesId = "997",
        seriesName = "Series 6",
        imageLink = "https://i2.seadn.io/polygon/0x96bed0ae3ae5b0f8a2b92a9315981af25080cf9f/1f2918b2f64905471b373fdb21b400/571f2918b2f64905471b373fdb21b400.png",
        videoLink = "https://raw2.seadn.io/polygon/0x96bed0ae3ae5b0f8a2b92a9315981af25080cf9f/2683651f9f497b89f4209445939631/8c2683651f9f497b89f4209445939631.mp4",
        customLink = "https://proof-of-wear.com/",
        partnerId = PARTNER_1,
    ),
    Product(
        id = "7",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product 998",
        description = "First unique T-shirt collection by Proof of Wear!",
        seriesId = "998",
        seriesName = "Series 7",
        imageLink = "https://i2.seadn.io/polygon/0x052f027da88d0a6a76515346615206d2457efc45/8728bc26abed65e90e83704c77d3e9/618728bc26abed65e90e83704c77d3e9.jpeg",
        videoLink = "https://raw2.seadn.io/polygon/0x052f027da88d0a6a76515346615206d2457efc45/7d5369795b7750eb22c0841db4d29f/2c7d5369795b7750eb22c0841db4d29f.mp4",
        customLink = "https://proof-of-wear.com/",
        partnerId = PARTNER_2,
    ),
    Product(
        id = "8",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product 999",
        description = "Przykładowy opis wydarzenia nr 999",
        seriesId = "999",
        seriesName = "Series 8",
        imageLink = "https://i2.seadn.io/polygon/0xc8b0f59f636b72752578a86c0337f1a4f675a40d/a256c49770bbf0ec4458d9ddd35274/e0a256c49770bbf0ec4458d9ddd35274.png",
        videoLink = "https://raw2.seadn.io/polygon/0xc8b0f59f636b72752578a86c0337f1a4f675a40d/6bbd5b933cec2f1321e49bf9b7d950/de6bbd5b933cec2f1321e49bf9b7d950.mp4",
        customLink = "https://proof-of-wear.com/",
        partnerId = PARTNER_2,
    ),
)