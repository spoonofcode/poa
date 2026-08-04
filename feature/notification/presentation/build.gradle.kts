plugins {
    alias(libs.plugins.convention.cmp.presentation)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.notification.domain)
            }
        }

        commonTest {
            dependencies {
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
