plugins {
    alias(libs.plugins.convention.buildkonfig)
    alias(libs.plugins.convention.cmp.feature)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.mokkery)
}

kotlin {
    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        ios.deploymentTarget = "18.0"

//        podfile = project.file("../../../iosApp/Podfile")

        pod("GoogleSignIn")

        framework {
            baseName = "FeatureAuth"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)

                implementation(projects.feature.auth.domain)
                implementation(projects.core.session.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.turbine)
                implementation(projects.core.presentationTest)
                implementation(projects.core.recaptcha)
                implementation(projects.feature.auth.dataTest)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.credentials)
                implementation(libs.androidx.credentials.play.service.auth)

                implementation(libs.google.id)
                implementation(libs.recaptcha)
            }
        }
        iosMain {
            dependencies {
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.feature.auth.presentation"
//    generateResClass = always
}