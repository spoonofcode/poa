plugins {
    alias(libs.plugins.convention.cmp.presentation)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.partner.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.turbine)
                implementation(projects.core.presentationTest)
                implementation(projects.feature.partner.dataTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.partner.presentation"
//    generateResClass = always
}
