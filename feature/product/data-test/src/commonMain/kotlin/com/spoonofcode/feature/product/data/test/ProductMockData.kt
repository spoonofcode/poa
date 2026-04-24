package com.spoonofcode.feature.product.data.test

import com.spoonofcode.feature.product.domain.model.Product
import kotlinx.datetime.LocalDateTime

object ProductMockData {
    val PRODUCT_1 = Product(
        id = "1",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product #1",
        description = "Przykładowy opis wydarzenia nr 1",
        seriesId = "1",
        seriesName = "Series 1",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_261/bluza-damska-z-kapturem-ocieplana-bordo.webp",
        customLink = "https://beautysaute.pl/",
        partnerId = "1",
    )
    val PRODUCT_2 = Product(
        id = "2",
        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
        name = "Product #2",
        description = "Przykładowy opis wydarzenia nr 2",
        seriesId = "2",
        seriesName = "Series 2",
        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_267/komplet-mocca-post.webp",
        customLink = "https://beautysaute.pl/",
        partnerId = "2",
    )

    val PRODUCTS = listOf(PRODUCT_1, PRODUCT_2)
}