plugins {
    alias(libs.plugins.convention.cmp.presentation)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.faq.presentation"
//    generateResClass = always
}
