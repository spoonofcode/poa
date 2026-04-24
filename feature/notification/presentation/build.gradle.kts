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
                implementation(libs.calf.permissions)

                implementation(projects.core.firebase.domain)
                implementation(projects.core.session.domain)
                implementation(projects.feature.notification.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.turbine)
                implementation(projects.core.presentationTest)
                implementation(projects.feature.notification.dataTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.notification.presentation"
//    generateResClass = always
}