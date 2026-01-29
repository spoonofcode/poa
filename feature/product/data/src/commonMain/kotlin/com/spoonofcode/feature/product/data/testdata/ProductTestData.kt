package com.spoonofcode.feature.product.data.testdata

import com.spoonofcode.poa.feature.product.domain.model.Product


val USER_1 = "11111111-1111-1111-1111-111111111111"
val USER_2 = "22222222-2222-2222-2222-222222222222"
val USER_3 = "33333333-3333-3333-3333-333333333333"

val PARTNER_1 = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
val PARTNER_2 = "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"

val PRODUCTS: List<Product> = listOf(
    Product(
        id = "1",
        name = "Product #1",
        description = "Przykładowy opis wydarzenia nr 1",
        seriesId = "1",
        collectionName = "Collection 1",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_261/bluza-damska-z-kapturem-ocieplana-bordo.webp",
        websiteLink = "https://beautysaute.pl/",
        customLink = "https://beautysaute.pl/",
        ownerUserId = USER_1,
        partnerId = PARTNER_1,
    ),
    Product(
        id = "2",
        name = "Product #2",
        description = "Przykładowy opis wydarzenia nr 2",
        seriesId = "2",
        collectionName = "Collection 1",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_267/komplet-mocca-post.webp",
        websiteLink = "https://beautysaute.pl/",
        customLink = "https://beautysaute.pl/",
        ownerUserId = USER_2,
        partnerId = PARTNER_1,
    ),
    Product(
        id = "3",
        name = "Product #3",
        description = "Przykładowy opis wydarzenia nr 3",
        seriesId = "3",
        collectionName = "Collection 1",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_259/Bluza-z-kapturem-damska-czarna-ocieplana.webp",
        websiteLink = "https://beautysaute.pl/",
        customLink = "https://beautysaute.pl/",
        ownerUserId = USER_3,
        partnerId = PARTNER_2,
    ),

    Product(
        id = "4",
        name = "Product #4",
        description = "Przykładowy opis wydarzenia nr 4",
        seriesId = "4",
        collectionName = "Collection 1",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_257/bluza-damska-z-kapturem-pudrowy-roz.webp",
        websiteLink = "https://beautysaute.pl/",
        customLink = "https://beautysaute.pl/",
        ownerUserId = USER_1,
        partnerId = PARTNER_2,
    ),
    Product(
        id = "5",
        name = "Product #5",
        description = "Przykładowy opis wydarzenia nr 5",
        seriesId = "5",
        collectionName = "Collection 1",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_262/Bluza-damska-bez-kaptura-czekolada.webp",
        websiteLink = "https://beautysaute.pl/",
        customLink = "https://beautysaute.pl/",
        ownerUserId = USER_2,
        partnerId = PARTNER_1,
    ),

    Product(
        id = "6",
        name = "Product 997",
        description = "First unique T-shirt collection by Proof of Wear!",
        tagId = "043469C2891D91",
        seriesId = "997",
        collectionName = "Collection 1",
        imageLink = "https://i2.seadn.io/polygon/0x96bed0ae3ae5b0f8a2b92a9315981af25080cf9f/1f2918b2f64905471b373fdb21b400/571f2918b2f64905471b373fdb21b400.png",
        videoLink = "https://raw2.seadn.io/polygon/0x96bed0ae3ae5b0f8a2b92a9315981af25080cf9f/2683651f9f497b89f4209445939631/8c2683651f9f497b89f4209445939631.mp4",
        websiteLink = "https://proof-of-wear.com/",
        customLink = "https://proof-of-wear.com/",
        ownerUserId = null,
        partnerId = PARTNER_1,
    ),
    Product(
        id = "7",
        name = "Product 998",
        description = "First unique T-shirt collection by Proof of Wear!",
        tagId = "047AECC2891D90",
        seriesId = "998",
        collectionName = "Collection 1",
        imageLink = "https://i2.seadn.io/polygon/0x052f027da88d0a6a76515346615206d2457efc45/8728bc26abed65e90e83704c77d3e9/618728bc26abed65e90e83704c77d3e9.jpeg",
        videoLink = "https://raw2.seadn.io/polygon/0x052f027da88d0a6a76515346615206d2457efc45/7d5369795b7750eb22c0841db4d29f/2c7d5369795b7750eb22c0841db4d29f.mp4",
        websiteLink = "https://proof-of-wear.com/",
        customLink = "https://proof-of-wear.com/",
        ownerUserId = null,
        partnerId = PARTNER_2,
    ),
    Product(
        id = "8",
        name = "Product 999",
        description = "Przykładowy opis wydarzenia nr 999",
        tagId = "04A971E2E51090",
        seriesId = "999",
        collectionName = "Collection 1",
        imageLink = "https://i2.seadn.io/polygon/0xc8b0f59f636b72752578a86c0337f1a4f675a40d/a256c49770bbf0ec4458d9ddd35274/e0a256c49770bbf0ec4458d9ddd35274.png",
        videoLink = "https://raw2.seadn.io/polygon/0xc8b0f59f636b72752578a86c0337f1a4f675a40d/6bbd5b933cec2f1321e49bf9b7d950/de6bbd5b933cec2f1321e49bf9b7d950.mp4",
        websiteLink = "https://proof-of-wear.com/",
        customLink = "https://proof-of-wear.com/",
        ownerUserId = null,
        partnerId = PARTNER_2,
    ),
)