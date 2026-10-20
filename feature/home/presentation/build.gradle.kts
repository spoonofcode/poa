plugins {
    alias(libs.plugins.convention.cmp.presentation)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.preview)
                implementation(projects.feature.home.domain)
                implementation(projects.feature.partner.domain)
                implementation(projects.feature.notification.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(projects.core.presentationTest)
                implementation(projects.core.firebase.dataTest)
                implementation(projects.feature.home.dataTest)
                implementation(projects.feature.partner.dataTest)
                implementation(projects.feature.notification.dataTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.home.presentation"
    generateResClass = always
}
