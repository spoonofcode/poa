plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.kotlin.cocoapods)
}

kotlin {
    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        ios.deploymentTarget = "18.0"

//        podfile = project.file("../../../iosApp/Podfile")

        pod("FirebaseMessaging")

        framework {
            baseName = "FeatureFirebase"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)

                implementation(projects.core.firebase.domain)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.firebase.messaging)
                implementation(project.dependencies.platform(libs.firebase.bom))
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}