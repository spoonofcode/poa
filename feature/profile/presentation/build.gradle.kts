plugins {
    alias(libs.plugins.convention.cmp.presentation)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.profile.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.turbine)
                implementation(projects.core.presentationTest)
                implementation(projects.feature.profile.dataTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.profile.presentation"
//    generateResClass = always
}
