plugins {
    alias(libs.plugins.convention.cmp.presentation)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.tac.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.turbine)
                implementation(projects.core.presentationTest)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.tac.presentation"
//    generateResClass = always
}