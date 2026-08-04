plugins {
    alias(libs.plugins.convention.cmp.presentation)
}

kotlin {
    sourceSets {
        commonTest {
            dependencies {
                implementation(projects.core.presentationTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.faq.presentation"
    generateResClass = always
}
