plugins {
    alias(libs.plugins.convention.cmp.presentation)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.product.domain)
                implementation(projects.feature.partner.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(projects.core.presentationTest)
                implementation(projects.core.firebase.dataTest)
                implementation(projects.feature.product.dataTest)
                implementation(projects.feature.notification.dataTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.product.presentation"
    generateResClass = always
}
