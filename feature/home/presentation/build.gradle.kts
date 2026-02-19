plugins {
    alias(libs.plugins.convention.cmp.feature)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.koin.core)

                implementation(projects.feature.home.domain)
                implementation(projects.feature.partner.domain)
                implementation(projects.feature.notification.domain)
                implementation(projects.core.firebase)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.turbine)
                implementation(projects.core.presentationTest)
                implementation(projects.feature.home.dataTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.home.presentation"
//    generateResClass = always
}