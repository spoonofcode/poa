plugins {
    alias(libs.plugins.convention.cmp.feature)
    alias(libs.plugins.kotlin.cocoapods)
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
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
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