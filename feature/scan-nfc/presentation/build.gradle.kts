plugins {
    alias(libs.plugins.convention.cmp.presentation)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.nfc)
                implementation(projects.feature.product.domain)
                implementation(projects.feature.scanNfc.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.turbine)
                implementation(projects.core.presentationTest)
                implementation(projects.feature.product.dataTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.scan.nfc.presentation"
//    generateResClass = always
}
